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

    public static final String sUrl1 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=oslo";
    public static final String sUrl2 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=stockholm";
    public static final String sUrl3 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=london";
    public static final String sUrl4 = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=gothenburg";

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
        //theCities.remove(randomCity);

        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.imageButton1);

        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
//        Drawable image2 = getResources().getDrawable(R.drawable.stockholm);
//        button2.setImageDrawable(image2);

        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
//        Drawable image3 = getResources().getDrawable(R.drawable.goteborg);
//        button3.setImageDrawable(image3);

        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.imageButton4);
//        Drawable image4 = getResources().getDrawable(R.drawable.london);
//        button4.setImageDrawable(image4);

        new DownloadURLTask(button1).execute(sUrl1,"1");
        new DownloadURLTask(button2).execute(sUrl2,"1");
        new DownloadURLTask(button3).execute(sUrl3,"0");
        new DownloadURLTask(button4).execute(sUrl4,"1");

        //final String fuckoff[] = {EnterNameFragment.downloadThisUrl.toString()};

//        if("http://d1fy7ceoqli51q.cloudfront.net/ImageVaultFiles/id_877/cf_432/oslo_kf_318x184.jpg".compareTo(fuckoff[0]) == 0){
//            Toast.makeText(getActivity(), "true" , Toast.LENGTH_SHORT).show();
//        }

        // fuckoff[0] = "http://d1fy7ceoqli51q.cloudfront.net/ImageVaultFiles/id_877/cf_432/oslo_kf_318x184.jpg";

//            Ion.with(button1)
//                    .placeholder(R.drawable.london)
//                    .error(R.drawable.london)
//                    .load(fuckoff);


//        Ion.with(this).load(sUrl1).asJsonObject().setCallback(new FutureCallback<JsonObject>(){
//
//            public static final String LOG_TAG = "GameplayFragment";
//            ImageButton button = button1;
//            String url = "";
//
//            @Override
//            public void onCompleted(Exception e, JsonObject result) {
//                if(e == null){
//                    JsonArray array = (JsonArray) result.getAsJsonObject("responseData").getAsJsonArray("results");
//                    JsonObject object = (JsonObject) array.get(0);
//                    url = object.get("url").toString();
//
//                    Picasso.with(getActivity()).load(url.substring(1, url.length() - 1)).into(button1);
//                }else{
//                    Log.e(LOG_TAG, "Could not fetch data", e);
//                }
//            }
//        });

        Chronometer timer = (Chronometer) rootView.findViewById(R.id.timer);
        timer.start();

        return rootView;
    }
}

