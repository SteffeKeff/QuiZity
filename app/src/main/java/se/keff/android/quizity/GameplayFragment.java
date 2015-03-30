package se.keff.android.quizity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public final class GameplayFragment extends Fragment {

    Game game;
    Chronometer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_gameplay, container, false);
        game = new Game(rootView);
        ImageButton button = (ImageButton) rootView.findViewById(R.id.imageButton1);
        button.setOnClickListener(listener1);
        button = (ImageButton) rootView.findViewById(R.id.imageButton2);
        button.setOnClickListener(listener2);
        button = (ImageButton) rootView.findViewById(R.id.imageButton3);
        button.setOnClickListener(listener3);
        button = (ImageButton) rootView.findViewById(R.id.imageButton4);
        button.setOnClickListener(listener4);

        game.startRound();
        timer = (Chronometer) rootView.findViewById(R.id.timer);
        timer.start();

        return rootView;
    }

    public void buttonClicked(ImageButton buttonId){
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
        // Toast.makeText(getActivity(), String.valueOf(stoppedMilliseconds), Toast.LENGTH_SHORT).show();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        game.nextRound(buttonId, stoppedMilliseconds);
    }

    View.OnClickListener listener1 = new View.OnClickListener(){
        public void onClick(View v){
            buttonClicked((ImageButton) v.findViewById(R.id.imageButton1));
        }
    };

    View.OnClickListener listener2 = new View.OnClickListener(){
        public void onClick(View v){
            buttonClicked((ImageButton) v.findViewById(R.id.imageButton2));
        }
    };

    View.OnClickListener listener3 = new View.OnClickListener(){
        public void onClick(View v){
            buttonClicked((ImageButton) v.findViewById(R.id.imageButton3));
        }
    };

    View.OnClickListener listener4 = new View.OnClickListener(){
        public void onClick(View v){
            buttonClicked((ImageButton) v.findViewById(R.id.imageButton4));
        }
    };

}

