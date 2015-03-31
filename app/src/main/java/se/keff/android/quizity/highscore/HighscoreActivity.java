package se.keff.android.quizity.highscore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import se.keff.android.quizity.R;

public final class HighscoreActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HighscoreFragmentSQL())
                    .commit();
        }
    }
}
