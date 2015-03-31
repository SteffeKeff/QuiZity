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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import se.keff.android.quizity.R;
import se.keff.android.quizity.game.Game;
import se.keff.android.quizity.game.GameActivity;

public final class HighscoreFragment extends Fragment {

    private ListView highscoreListView;

    private final ArrayList<HighscorePerson> highscorePersons = new ArrayList<>();
    private MediaPlayer highscoreMusic;

    private final HashMap<String, Integer> fetchedDataToHashMap = new HashMap<String, Integer>();
    private final ValueComparator valueComparator = new ValueComparator(fetchedDataToHashMap);
    private final TreeMap<String, Integer> fetchedDataSortedInTreeMap = new TreeMap<String, Integer>(valueComparator);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highscore, container, false);

        if (!GameActivity.playerName.isEmpty()) {

            highscoreMusic = MediaPlayer.create(getActivity(), R.raw.jazz);
            highscoreMusic.start();
            SharedPreferences names = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = names.edit();
            int totalScore = Game.score * 10 - Game.totalTime;

            editor.putInt(GameActivity.playerName, totalScore);

            editor.commit();

            GameActivity.playerName = "";
            Game.score = 0;
            Game.totalTime = 0;
        }

        SharedPreferences savedData = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);

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

final class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}