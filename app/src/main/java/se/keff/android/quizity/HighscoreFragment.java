package se.keff.android.quizity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HighscoreFragment extends Fragment {

    private ListView highscoreListView;
    ArrayList<String> highscore = new ArrayList<>();
    TreeMap<Integer, String> savedData = new TreeMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.highscore_layout, container, false);

        if(!GameActivity.playerName.isEmpty()){

            SharedPreferences names = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = names.edit();
            int totalScore = Game.score*10 - Game.totalTime;

            editor.putInt(GameActivity.playerName, totalScore);

            editor.commit();

            GameActivity.playerName = "";
            Game.score = 0;
            Game.totalTime = 0;
        }

        SharedPreferences playerNames = getActivity().getSharedPreferences("highscore", Context.MODE_PRIVATE);

        Map<String, ?> players = playerNames.getAll();

        for(Map.Entry<String,?> entry : players.entrySet()){
            savedData.put(Integer.parseInt(entry.getValue().toString()), entry.getKey());
        }

        for(Map.Entry<Integer,String> entry : savedData.entrySet()) {
            highscore.add("Name: " + entry.getValue() + ", score: " + entry.getKey().toString());
        }

        Collections.reverse(highscore);

        populateListView(rootView);

        return rootView;
    }

    public void populateListView(View rootView){
        highscoreListView = (ListView) rootView.findViewById(R.id.highscoreList);

        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, highscore);

        highscoreListView.setAdapter(objAdapter);
    }
}