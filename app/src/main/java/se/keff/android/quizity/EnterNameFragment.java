package se.keff.android.quizity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by Steffe on 15-03-26.
 */
public final class EnterNameFragment extends DialogFragment {

    View rootView;
    public static final String sUrl = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=oslo";
    private static final String LOG_TAG = "DownloadURLTask";
    public static String downloadThisUrl = "";

    public EnterNameFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_name, container, false);

        startDownload();

        return rootView;
    }

    public void startDownload(){

        Ion.with(this).load(sUrl).asJsonObject().setCallback(new FutureCallback<JsonObject>(){

            String url = "";

            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if(e == null){
                    JsonArray array = (JsonArray) result.getAsJsonObject("responseData").getAsJsonArray("results");
                    JsonObject object = (JsonObject) array.get(0);
                    url = object.get("url").toString();
                    downloadThisUrl = url;
                }else{
                    Log.e(LOG_TAG, "Could not fetch data", e);
                }
            }

        });
    }
}
