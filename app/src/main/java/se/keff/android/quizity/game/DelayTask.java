package se.keff.android.quizity.game;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import se.keff.android.quizity.R;

public final class DelayTask extends AsyncTask<Void, Integer, Integer> {
    private final TextView mCounterTextView;

    public DelayTask(TextView counterTextView) {
        mCounterTextView = counterTextView;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int counter = 0;
        while (counter < Game.originalCities.size()) {
            SystemClock.sleep(4000 / Game.originalCities.size());
            publishProgress(counter);
            counter++;
        }
        return counter;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        final Animation animAlpha = AnimationUtils.loadAnimation(mCounterTextView.getContext(), R.anim.anim_rotate);


         mCounterTextView.startAnimation(animAlpha);


        mCounterTextView.setText(Game.originalCities.get(progress[0]));
    }

    @Override
    protected void onPostExecute(Integer result) {
        for(ImageButton button: GameplayFragment.buttons){
            button.setVisibility(View.VISIBLE);
        }
        GameplayFragment.timer.setBase(SystemClock.elapsedRealtime());
        GameplayFragment.timer.start();
        mCounterTextView.setText(Game.correctCity);
    }
}