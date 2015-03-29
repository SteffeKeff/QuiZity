package se.keff.android.quizity;

import android.view.View;
import android.widget.ImageButton;

import java.util.concurrent.ExecutionException;

/**
 * Created by Steffe on 15-03-28.
 */
public final class Game {

    private static String url = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=";
    private static StringBuilder builder = new StringBuilder(url);
    public int score;
    public int totalTime;

    public void getImages(View rootView){

        String[] cities = rootView.getResources().getStringArray(R.array.cities);
        String[] citiesImageNumber = rootView.getResources().getStringArray(R.array.citiesImageNumber);

        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.imageButton1);
        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.imageButton4);

        try {
            builder.append(cities[0]);
            String b1Result = new DownloadURLTask(button1).execute(builder.toString(), citiesImageNumber[0]).get();
            builder.replace(0,builder.capacity(),url);
            builder.append(cities[1]);
            String b2Result = new DownloadURLTask(button2).execute(builder.toString(), citiesImageNumber[1]).get();
            builder.replace(0,builder.capacity(),url);
            builder.append(cities[2]);
            String b3Result = new DownloadURLTask(button3).execute(builder.toString(), citiesImageNumber[2]).get();
            builder.replace(0,builder.capacity(),url);
            builder.append(cities[3]);
            String b4Result = new DownloadURLTask(button4).execute(builder.toString(), citiesImageNumber[3]).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
