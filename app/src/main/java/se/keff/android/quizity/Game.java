package se.keff.android.quizity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public final class Game
{
    private final View rootView;
    private static String url = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=";
    private static StringBuilder builder = new StringBuilder(url);
    private final ArrayList<String> cities;
    private final ArrayList<String> citiesIndexNumbers;
    private int cities_size;
    private int round = 0;
    public int score;
    public int totalTime;
    private String correctCity;

    public Game(View rootView)
    {
        this.rootView = rootView;
        String[] citiesArray = rootView.getResources().getStringArray(R.array.cities);
        String[] citiesImageNumbersArray = rootView.getResources().getStringArray(R.array.citiesImageNumber);
        cities = new ArrayList(Arrays.asList(citiesArray));
        citiesIndexNumbers = new ArrayList(Arrays.asList(citiesImageNumbersArray));
        cities_size = cities.size();
    }

    public void getImages(ArrayList<String> displayCities, ArrayList<String> displayCitiesImageNumbers)
    {
        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.imageButton1);
        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.imageButton4);

        int randomCity = (int) ((Math.random() * displayCities.size()));

        try
        {
            builder.append(displayCities.get(randomCity));
            new DownloadURLTask(button1).execute(builder.toString(), displayCitiesImageNumbers.get(randomCity)).get();
            button1.setTag(displayCities.get(randomCity));
            builder.replace(0, builder.capacity(), url);
            displayCities.remove(randomCity);
            displayCitiesImageNumbers.remove(randomCity);

            randomCity = (int) ((Math.random() * displayCities.size()));
            builder.append(displayCities.get(randomCity));
            new DownloadURLTask(button2).execute(builder.toString(), displayCitiesImageNumbers.get(randomCity)).get();
            button2.setTag(displayCities.get(randomCity));
            builder.replace(0, builder.capacity(), url);
            displayCities.remove(randomCity);
            displayCitiesImageNumbers.remove(randomCity);

            randomCity = (int) ((Math.random() * displayCities.size()));
            builder.append(displayCities.get(randomCity));
            new DownloadURLTask(button3).execute(builder.toString(), displayCitiesImageNumbers.get(randomCity)).get();
            button3.setTag(displayCities.get(randomCity));
            builder.replace(0, builder.capacity(), url);
            displayCities.remove(randomCity);
            displayCitiesImageNumbers.remove(randomCity);

            randomCity = (int) ((Math.random() * displayCities.size()));
            builder.append(displayCities.get(randomCity));
            new DownloadURLTask(button4).execute(builder.toString(), displayCitiesImageNumbers.get(randomCity)).get();
            button4.setTag(displayCities.get(randomCity));
            builder.replace(0, builder.capacity(), url);
            displayCities.remove(randomCity);
            displayCitiesImageNumbers.remove(randomCity);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    public void nextRound(ImageButton buttonClicked, int stoppedMilliseconds)
    {
        if (correctCity == buttonClicked.getTag())
        {
            TextView displayScore = (TextView) rootView.findViewById(R.id.score);
            TextView displayTime = (TextView) rootView.findViewById(R.id.total_time);
            score++;
            displayScore.setText("Score: " + score);
            totalTime += stoppedMilliseconds;
            displayTime.setText("Total time: " + (totalTime/1000));

        }

        if (!cities.isEmpty()){
            startRound();
        }
    }

    public void startRound()
    {
        round++;
        TextView displayRound = (TextView) rootView.findViewById(R.id.round);
        displayRound.setText(displayRound.getResources().getString(R.string.round) + String.valueOf(round) + "/" + (cities_size/4));

        ArrayList<String> displayCities = new ArrayList<>();
        ArrayList<String> displayCitiesIndexNumbers = new ArrayList<>();

        int randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        TextView displayCityName = (TextView) rootView.findViewById(R.id.cityName);
        displayCityName.setText(displayCities.get(0));
        correctCity = displayCities.get(0);
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);

        randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);

        randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);

        randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);

        getImages(displayCities, displayCitiesIndexNumbers);
    }
}
