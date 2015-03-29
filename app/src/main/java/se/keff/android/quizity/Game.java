package se.keff.android.quizity;

import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by Steffe on 15-03-28.
 */
public final class Game {

    private static String url = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=";
    private static StringBuilder builder1 = new StringBuilder(url);
    private static StringBuilder builder2 = new StringBuilder(url);
    private static StringBuilder builder3 = new StringBuilder(url);
    private static StringBuilder builder4 = new StringBuilder(url);

    public void startDownload(View rootView){

        String[] cities = rootView.getResources().getStringArray(R.array.cities);
        String[] citiesImageNumber = rootView.getResources().getStringArray(R.array.citiesImageNumber);

        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.imageButton1);
        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.imageButton4);

        builder1.append(cities[0]);
        builder2.append(cities[1]);
        builder3.append(cities[2]);
        builder4.append(cities[3]);
        try {
            String b1Result = new DownloadURLTask(button1).execute(builder1.toString(), citiesImageNumber[0]).get();
        //builder.replace(0,builder.capacity(),url);
            String b2Result = new DownloadURLTask(button2).execute(builder2.toString(), citiesImageNumber[1]).get();
        //builder.replace(0,builder.capacity(),url);
            String b3Result = new DownloadURLTask(button3).execute(builder3.toString(), citiesImageNumber[2]).get();
        //builder.replace(0,builder.capacity(),url);
            String b4Result = new DownloadURLTask(button4).execute(builder4.toString(), citiesImageNumber[3]).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally{
            Toast.makeText(button1.getContext(), "klar!", Toast.LENGTH_SHORT).show();
        }

    }
}
