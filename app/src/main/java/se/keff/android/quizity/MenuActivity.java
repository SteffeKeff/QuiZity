package se.keff.android.quizity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public final class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        MediaPlayer pl = MediaPlayer.create(this, R.raw.game);
        pl.start();
               if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MenuFragment())
                    .commit();

        }


    }
    public void playGame(View view)
    {
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        final Button button = (Button) findViewById(R.id.buttonPlay);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    public void animButton(View view){

        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        final Button button = (Button) findViewById(R.id.buttonHighscore);
        view.startAnimation(animAlpha);
    }
    public void animButton2(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        final Button button = (Button) findViewById(R.id.buttonSettings);
        view.startAnimation(animAlpha);

    }
    public void animButton3(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        final Button button = (Button) findViewById(R.id.buttonHowTo);
        view.startAnimation(animAlpha);

    }


}
