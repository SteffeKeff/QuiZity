package se.keff.android.quizity;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadURLTask extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG = "DownloadURLTask";
    public ImageButton button;

    public DownloadURLTask(ImageButton mImageButton) {
        button = mImageButton;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                result.append(line);
            }

            JsonParser parser = new JsonParser();
            Object obj = parser.parse(result.toString());
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray array = (JsonArray) jsonObject.getAsJsonObject("responseData").getAsJsonArray("results");
            //Om man vill välja vilket nummer av bilden, t.ex. nr1(0) lr nr2(1)
            //JsonObject object = (JsonObject) array.get(Integer.parseInt(params[1]));
            JsonObject object = (JsonObject) array.get(0);
            result.delete(0, result.capacity());
            result.append(object.get("url").toString());

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Could not start download", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not start download", e);
        }
        finally
        {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Could not close stream", e);
                }
            }
        }
        //tar bort första och sista för att få bort " och "
        return result.toString().substring(1, result.length() - 1);
    }

    @Override
    protected void onPostExecute(String result) {
        Picasso.with(button.getContext()).load(result).into(button);
    }
}
