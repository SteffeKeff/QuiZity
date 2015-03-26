package se.keff.android.quizity;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public final class GameplayFragment extends Fragment{


    public GameplayFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gameplay, container,false);

        TextView cityName = (TextView) rootView.findViewById(R.id.cityName);

        String cities[] = getResources().getStringArray(R.array.cities);
        List<String> theCities = Arrays.asList(cities);

        int randomCity = (int) ((Math.random()*5));

        String theCity = theCities.get(randomCity);

        cityName.setText(theCity);
        //theCities.remove(randomCity);

        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.imageButton1);
        Drawable image1 = getResources().getDrawable(R.drawable.malmo);
        button1.setImageDrawable(image1);

        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
        Drawable image2 = getResources().getDrawable(R.drawable.stockholm);
        button2.setImageDrawable(image2);

        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
        Drawable image3 = getResources().getDrawable(R.drawable.goteborg);
        button3.setImageDrawable(image3);

        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.imageButton4);
        Drawable image4 = getResources().getDrawable(R.drawable.london);
        button4.setImageDrawable(image4);


        Chronometer timer = (Chronometer) rootView.findViewById(R.id.timer);
        timer.start();

        return rootView;
    }
}
