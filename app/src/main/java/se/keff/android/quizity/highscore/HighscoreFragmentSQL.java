package se.keff.android.quizity.highscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import se.keff.android.quizity.R;
import se.keff.android.quizity.game.Game;
import se.keff.android.quizity.game.GameActivity;
import se.keff.android.quizity.game.GameplayFragment;

public final class HighscoreFragmentSQL extends Fragment {

    private ListView highscoreListView;
    private final static String sUrl = "http://83.251.232.254/HighscoreServer/highscore";

    private final ArrayList<HighscorePerson> highscorePersons = new ArrayList<>();
    private MediaPlayer highscoreMusic;

    private final HashMap<String, Integer> fetchedDataToHashMap = new HashMap<String, Integer>();
    private final ValueComparator valueComparator = new ValueComparator(fetchedDataToHashMap);
    private final TreeMap<String, Integer> fetchedDataSortedInTreeMap = new TreeMap<String, Integer>(valueComparator);

    SharedPreferences savedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highscore, container, false);

        if (!GameActivity.playerName.isEmpty()) {

            highscoreMusic = MediaPlayer.create(getActivity(), R.raw.jazz);
            highscoreMusic.start();

//            savedData = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = savedData.edit();
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
                            for(JsonElement element: result){
                               JsonObject object = element.getAsJsonObject();

                               JsonElement name = object.get("name");
                               String highScoreName = name.getAsString();

                               JsonElement score = object.get("score");
                               String highScoreScore = name.getAsString();
                            }
                        }
                    });

            //editor.putInt(GameActivity.playerName, totalScore);

            //editor.commit();

            GameActivity.playerName = "";
            Game.score = 0;
            Game.totalTime = 0;
        }

        savedData = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);

        Map<String, ?> fetchedData = savedData.getAll();

        for (Map.Entry<String, ?> entry : fetchedData.entrySet()) {
            fetchedDataToHashMap.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
        }

        fetchedDataSortedInTreeMap.putAll(fetchedDataToHashMap);

        for (Map.Entry<String, Integer> entry : fetchedDataSortedInTreeMap.entrySet()) {
            String name = entry.getKey();
            String score = entry.getValue().toString();
            //padding på score för att få det lite snyggare
            String paddedScore = padRight(score, 8-score.length());

            highscorePersons.add(new HighscorePerson(name, paddedScore));
            //presenterar top 15
            if(highscorePersons.size() >= 15){break;}
        }

        populateListView(rootView);

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