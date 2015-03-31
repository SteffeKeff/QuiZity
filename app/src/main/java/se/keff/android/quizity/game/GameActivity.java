package se.keff.android.quizity.game;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import se.keff.android.quizity.R;
import se.keff.android.quizity.highscore.HighscorePerson;

public final class GameActivity extends ActionBarActivity {
    public static String playerName = "";
    private final static String sUrl = "http://83.251.232.254/HighscoreServer/checkName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new EnterNameFragment())
                    .commit();
        }
    }

    public void startGame(final View view) {

        final EditText enteredName = (EditText) findViewById(R.id.name);
        String errorMessage = getResources().getString(R.string.errorNoName);
        final String invalidName = getResources().getString(R.string.nameAlreadyExists);

        JsonObject json = new JsonObject();
        json.addProperty("name", enteredName.getText().toString().trim());
        json.addProperty("score", 0);

        if (enteredName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            Ion.with(this)
                    .load(sUrl)
                    .setJsonObjectBody(json)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            Boolean validName = result.get("valid").getAsBoolean();
                            if (validName) {
                                playerName = enteredName.getText().toString().trim();
                                hideKeyboard(view.getWindowToken());
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.container, new GameplayFragment())
                                        .commit();
                            } else {
                                Toast.makeText(view.getContext(), invalidName, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void hideKeyboard(IBinder binder) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(binder, 0);
    }
}
