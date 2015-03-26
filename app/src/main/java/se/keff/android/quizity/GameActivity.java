package se.keff.android.quizity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

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
