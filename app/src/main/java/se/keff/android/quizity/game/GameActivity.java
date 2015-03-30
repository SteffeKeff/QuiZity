package se.keff.android.quizity.game;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import se.keff.android.quizity.R;

public final class GameActivity extends ActionBarActivity
{
    public static String playerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new EnterNameFragment())
                    .commit();
        }
    }

    public void startGame(View view)
    {

        EditText enteredName = (EditText) findViewById(R.id.name);
        String errorMessage = getResources().getString(R.string.errorNoName);

        if (enteredName.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        } else
        {
            playerName = enteredName.getText().toString().trim();
            hideKeyboard(view.getWindowToken());
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new GameplayFragment())
                    .commit();
        }
    }

    private void hideKeyboard(IBinder binder)
    {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(binder, 0);
    }
}
