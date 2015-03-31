package se.keff.android.quizity.highscore;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;

import se.keff.android.quizity.R;
import se.keff.android.quizity.game.Game;
import se.keff.android.quizity.game.GameActivity;

public final class HighscoreFragmentSQL extends Fragment {

    private ListView highscoreListView;
    private final static String sUrl = "http://83.251.232.254/HighscoreServer/highscore";

    private final ArrayList<HighscorePerson> highscorePersons = new ArrayList<>();
    private MediaPlayer highscoreMusic;

    private final HashMap<String, Integer> fetchedDataToHashMap = new HashMap<String, Integer>();
    private final ValueComparator valueComparator = new ValueComparator(fetchedDataToHashMap);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_highscore, container, false);

        if (!GameActivity.playerName.isEmpty()) {

            highscoreMusic = MediaPlayer.create(getActivity(), R.raw.jazz);
            highscoreMusic.start();

            int totalScore = Game.score * 10 - Game.totalTime;

            JsonObject json = new JsonObject();
            json.addProperty("name", GameActivity.playerName);
            json.addProperty("score", totalScore);
            Ion.with(this)
                    .load(sUrl)
                    .setJsonObjectBody(json)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {
                            for (int i = 0; i < result.size(); i++) {
                                JsonObject object = result.get(i).getAsJsonObject();
                                JsonElement name = object.get("name");
                                String highScoreName = name.getAsString();

                                JsonElement score = object.get("score");
                                String highScoreScore = score.getAsString();
                                String paddedScore = padRight(highScoreScore, 8 - highScoreScore.length());
                                HighscorePerson person = new HighscorePerson(highScoreName, paddedScore);

                                highscorePersons.add(person);
                            }
                            populateListView(rootView);
                        }
                    });

            GameActivity.playerName = "";
            Game.score = 0;
            Game.totalTime = 0;
        } else {
            Ion.with(getActivity())
                    .load(sUrl)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            for (int i = 0; i < result.size(); i++) {
                                JsonObject object = result.get(i).getAsJsonObject();
                                JsonElement name = object.get("name");
                                String highScoreName = name.getAsString();

                                JsonElement score = object.get("score");
                                String highScoreScore = score.getAsString();
                                String paddedScore = padRight(highScoreScore, 8 - highScoreScore.length());
                                HighscorePerson person = new HighscorePerson(highScoreName, paddedScore);

                                highscorePersons.add(person);
                            }
                            populateListView(rootView);
                        }
                    });
        }

        return rootView;
    }

    public void populateListView(View rootView) {
        highscoreListView = (ListView) rootView.findViewById(R.id.highscoreList);

        HighscoreListAdapter adapter = new HighscoreListAdapter(getActivity(), highscorePersons);

        highscoreListView.setAdapter(adapter);
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

}