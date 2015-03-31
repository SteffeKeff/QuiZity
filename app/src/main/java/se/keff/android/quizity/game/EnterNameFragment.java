package se.keff.android.quizity.game;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import se.keff.android.quizity.R;


public final class EnterNameFragment extends DialogFragment {

    View rootView;

    public EnterNameFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_name, container, false);
        ImageView v = (ImageView) rootView.findViewById(R.id.imageView2);
        Ion.with(v).load("http://media.giphy.com/media/WKzqOcDyPCld6/giphy.gif");
        return rootView;
    }
}
