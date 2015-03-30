package se.keff.android.quizity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HighscoreFragment extends Fragment {

    private ListView myListView;
    private String[] strListView;
    String items[] = {"blue","green","red","black","purple","yellow","white","grey"};
//    ArrayList<String> highscoreNames = new ArrayList<>();
//    ArrayList<Integer> highscoreScores = new ArrayList<>();
    ArrayList<String> highscore = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.highscore_layout, container, false);

//        if(sharedPref.contains("name")){
//            String currentPlayer = sharedPref.getString("name", "tomt");
//            int currentScore = sharedPref.getString("name", "tomt");
//            int current = sharedPref.getString("name", "tomt");
//            Toast.makeText(this, currentPlayer, Toast.LENGTH_SHORT ).show();
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.remove("name").commit();
//        }

        if(!GameActivity.playerName.isEmpty()){
            Toast.makeText(getActivity(), GameActivity.playerName, Toast.LENGTH_SHORT).show();

            SharedPreferences names = getActivity().getSharedPreferences("names", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = names.edit();
            int totalScore = Game.score*10 - Game.totalTime;
            editor.putInt(GameActivity.playerName, totalScore);

            editor.commit();

            GameActivity.playerName = "";
        }else{
            Toast.makeText(getActivity(), "From menu", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences playerNames = getActivity().getSharedPreferences("names", Context.MODE_PRIVATE);
        SharedPreferences playerScores = getActivity().getSharedPreferences("scores", Context.MODE_PRIVATE);
        SharedPreferences playerTimes = getActivity().getSharedPreferences("times", Context.MODE_PRIVATE);

        Map<String, ?> players = playerNames.getAll();

        for(Map.Entry<String,?> entry : players.entrySet()){
//            highscoreNames.add(entry.getKey());
//            highscoreScores.add(Integer.parseInt(entry.getValue().toString()));
            highscore.add("Name: " + entry.getKey() + ", score: " + entry.getValue().toString());
        }

        populateListView(rootView);

        return rootView;
    }

    public void populateListView(View rootView){
        myListView = (ListView) rootView.findViewById(R.id.highscoreList);

        strListView = getResources().getStringArray(R.array.cities);

        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, highscore);

        myListView.setAdapter(objAdapter);
    }
}