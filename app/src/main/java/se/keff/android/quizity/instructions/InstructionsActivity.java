package se.keff.android.quizity.instructions;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import se.keff.android.quizity.R;

public class InstructionsActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new InstructionsFragment())
                    .commit();
        }
    }
}
