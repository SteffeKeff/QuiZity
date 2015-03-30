package se.keff.android.quizity.menu;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import se.keff.android.quizity.R;

public final class MenuFragment extends Fragment {

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        final Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        final TextView buttonInstructions = (TextView) rootView.findViewById(R.id.buttonInstructions);
        final TextView buttonHighscore = (TextView) rootView.findViewById(R.id.buttonHighscore);
        final TextView buttonPlay = (TextView) rootView.findViewById(R.id.buttonPlay);
        final TextView buttonRightAngle1 = (TextView) rootView.findViewById(R.id.arrow1);
        final TextView buttonRightAngle2 = (TextView) rootView.findViewById(R.id.arrow2);
        final TextView buttonRightAngle3 = (TextView) rootView.findViewById(R.id.arrow3);

        buttonInstructions.setTypeface(tp);
        buttonHighscore.setTypeface(tp);
        buttonPlay.setTypeface(tp);
        buttonRightAngle1.setTypeface(tp);
        buttonRightAngle2.setTypeface(tp);
        buttonRightAngle3.setTypeface(tp);

        return rootView;
    }
}
