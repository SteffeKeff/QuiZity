package se.keff.android.quizity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public final class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MenuFragment())
                    .commit();
        }
    }

    public void playGame(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view)
    {
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }
}
