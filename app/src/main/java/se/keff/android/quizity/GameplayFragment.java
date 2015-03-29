package se.keff.android.quizity;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public final class GameplayFragment extends Fragment {

//    public static final String sUrl1 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=oslo";
//    public static final String sUrl2 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=stockholm";
//    public static final String sUrl3 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=london";
//    public static final String sUrl4 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=gothenburg";

    public GameplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_gameplay, container, false);

        TextView cityName = (TextView) rootView.findViewById(R.id.cityName);

        String cities[] = getResources().getStringArray(R.array.cities);
        List<String> theCities = Arrays.asList(cities);

        int randomCity = (int) ((Math.random() * 5));
        String theCity = theCities.get(randomCity);
        cityName.setText(theCity);

        new Game().startDownload(rootView);
        //theCities.remove(randomCity);

        Chronometer timer = (Chronometer) rootView.findViewById(R.id.timer);
        timer.start();

        return rootView;
    }
}

