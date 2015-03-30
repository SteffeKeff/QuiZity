package se.keff.android.quizity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public final class HighscoreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HighscoreFragment())
                    .commit();
        }
    }
}
