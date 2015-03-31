package se.keff.android.quizity.game;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public final class DelayTask extends AsyncTask<Void, Integer, Integer> {
    private final TextView mCounterTextView;
    private final ArrayList<String> cities;
    private final ArrayList<ImageButton> buttons;
    private final Chronometer timer;

    public DelayTask(TextView counterTextView, ArrayList<String> cities, ArrayList<ImageButton> buttons, Chronometer timer) {
        mCounterTextView = counterTextView;
        this.buttons = buttons;
        this.cities = cities;
        this.timer = timer;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int counter = 0;
        while (counter < cities.size()) {
            SystemClock.sleep(250);
            publishProgress(counter);
            counter++;
        }

        return counter;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mCounterTextView.setText(cities.get(progress[0]));
    }

    @Override
    protected void onPostExecute(Integer result) {
        for(ImageButton button: buttons){
            button.setVisibility(View.VISIBLE);
        }
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        mCounterTextView.setText(Game.correctCity);
    }
}










