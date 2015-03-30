package se.keff.android.quizity.menu;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import se.keff.android.quizity.R;

public final class MenuFragment extends Fragment {

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        final  Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        final Button button = (Button) rootView.findViewById(R.id.buttonPlay);
        final Button button2 = (Button) rootView.findViewById(R.id.buttonInstructions);
        final Button button3 = (Button) rootView.findViewById(R.id.buttonHighscore);

        button2.setTypeface(tp);
        button3.setTypeface(tp);
        button.setTypeface(tp);

        return rootView;
    }
}
