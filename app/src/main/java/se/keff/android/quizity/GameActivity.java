package se.keff.android.quizity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import se.keff.android.quizity.game.EnterNameFragment;
import se.keff.android.quizity.game.GameplayFragment;

public final class GameActivity extends ActionBarActivity{

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

    public void startGame(View view){

        EditText name = (EditText) findViewById(R.id.name);

        String errorMessage = getResources().getString(R.string.errorNoName);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Button button = (Button) findViewById(R.id.startButton);

        if(name.getText().toString().trim().isEmpty()){
            Toast.makeText(this ,errorMessage, Toast.LENGTH_SHORT).show();
        }else{
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

    public void buttonAnswer(View view){

        switch(view.getId()){
            case R.id.imageButton1: Toast.makeText(this, "FEEEEl", Toast.LENGTH_LONG).show(); break;
            case R.id.imageButton2: Toast.makeText(this, "RÄTT!!", Toast.LENGTH_LONG).show(); break;
            case R.id.imageButton3: Toast.makeText(this, "FEEEEEL!", Toast.LENGTH_LONG).show(); break;
            case R.id.imageButton4: Toast.makeText(this, "FEEEEL!", Toast.LENGTH_LONG).show(); break;
        }
    }
}
