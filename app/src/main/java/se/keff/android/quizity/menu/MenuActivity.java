package se.keff.android.quizity.menu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import se.keff.android.quizity.highscore.HighscoreActivity;
import se.keff.android.quizity.R;
import se.keff.android.quizity.game.GameActivity;
import se.keff.android.quizity.instructions.InstructionsActivity;

public final class MenuActivity extends ActionBarActivity
{
    private MediaPlayer menuMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        menuMusic = MediaPlayer.create(this, R.raw.game);
        menuMusic.start();
        menuMusic.setLooping(true);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MenuFragment())
                    .commit();
        }
    }

    public void playGame(View view)
    {
        menuMusic.setLooping(false);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view)
    {
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        view.startAnimation(animAlpha);
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    public void showInstructions(View view)
    {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }


}
