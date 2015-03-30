package se.keff.android.quizity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public final class HighscoreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //SharedPreferences sharedPref = getSharedPreferences("currentPlayer", Context.MODE_PRIVATE);

//        if(sharedPref.contains("name")){
//            String currentPlayer = sharedPref.getString("name", "tomt");
//            int currentScore = sharedPref.getString("name", "tomt");
//            int current = sharedPref.getString("name", "tomt");
//            Toast.makeText(this, currentPlayer, Toast.LENGTH_SHORT ).show();
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.remove("name").commit();
//        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HighscoreFragment())
                    .commit();
        }
    }
}
