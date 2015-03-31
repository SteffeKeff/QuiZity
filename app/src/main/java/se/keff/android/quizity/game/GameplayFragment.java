package se.keff.android.quizity.game;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.util.ArrayList;

import se.keff.android.quizity.R;

public final class GameplayFragment extends Fragment {

    Game game;
    public static Chronometer timer;
    public static ArrayList<ImageButton> buttons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_gameplay, container, false);
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton1));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton2));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton3));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton4));
        buttons.get(0).setOnClickListener(listener1);
        buttons.get(1).setOnClickListener(listener2);
        buttons.get(2).setOnClickListener(listener3);
        buttons.get(3).setOnClickListener(listener4);

        timer = (Chronometer) rootView.findViewById(R.id.timer);
        game = new Game(rootView);
        game.startRound();
        //timer.start();

        return rootView;
    }

    public void buttonClicked(ImageButton buttonId) {
        int stoppedMilliseconds = 0;
        timer.stop();
        String chronoText = timer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                    + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                    + Integer.parseInt(array[1]) * 60 * 1000
                    + Integer.parseInt(array[2]) * 1000;
        }
        //timer.start();
        for(ImageButton button: buttons){
            button.setVisibility(View.INVISIBLE);
        }
        game.nextRound(buttonId, stoppedMilliseconds);
    }

    View.OnClickListener listener1 = new View.OnClickListener() {
        public void onClick(View v) {
            buttonClicked(buttons.get(0));
        }
    };

    View.OnClickListener listener2 = new View.OnClickListener() {
        public void onClick(View v) {
            buttonClicked(buttons.get(1));
        }
    };

    View.OnClickListener listener3 = new View.OnClickListener() {
        public void onClick(View v) {
            buttonClicked(buttons.get(2));
        }
    };

    View.OnClickListener listener4 = new View.OnClickListener() {
        public void onClick(View v) {
            buttonClicked(buttons.get(3));
        }
    };

}

