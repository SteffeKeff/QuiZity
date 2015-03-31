package se.keff.android.quizity.game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import se.keff.android.quizity.R;
import se.keff.android.quizity.highscore.HighscoreActivity;

public final class Game {
    private final View rootView;
    private static final String URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=";
    private StringBuilder builder = new StringBuilder(URL);
    public static ArrayList<String> cities;
    public static ArrayList<String> originalCities;
    private final ArrayList<String> citiesIndexNumbers;
    private final ArrayList<String> displayCities;
    private final ArrayList<String> displayCitiesIndexNumbers;

    public static String correctCity;
    private int cities_size;
    private int round = 0;
    private int randomDisplayCity;
    private int randomCity;
    public static int score;
    public static int totalTime;

    public Game(View rootView) {
        this.rootView = rootView;

//        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton1));
//        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton2));
//        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton3));
//        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton4));

        for(ImageButton button: GameplayFragment.buttons){
            button.setVisibility(View.INVISIBLE);
        }

        String[] citiesArray = rootView.getResources().getStringArray(R.array.cities);
        String[] citiesImageNumbersArray = rootView.getResources().getStringArray(R.array.citiesImageNumber);
        cities = new ArrayList(Arrays.asList(citiesArray));
        originalCities = (ArrayList<String>) cities.clone();
        citiesIndexNumbers = new ArrayList(Arrays.asList(citiesImageNumbersArray));
        displayCities = new ArrayList<>();
        displayCitiesIndexNumbers = new ArrayList<>();

        cities_size = cities.size();
    }

    public void getImages() {

        Log.d("TAG", String.valueOf(displayCities.size()));
        try {
            for (ImageButton button : GameplayFragment.buttons) {
                loadImage(button);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void nextRound(ImageButton buttonClicked, int stoppedMilliseconds) {
        MediaPlayer media = MediaPlayer.create(rootView.getContext(),R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(rootView.getContext(),R.raw.wrong);
        if (correctCity == buttonClicked.getTag()) {

            media.start();
            TextView displayScore = (TextView) rootView.findViewById(R.id.score);
            TextView displayTime = (TextView) rootView.findViewById(R.id.total_time);

            score++;
            if (stoppedMilliseconds > 0 && stoppedMilliseconds < 10000) {
                totalTime += (stoppedMilliseconds / 1000);
            } else if (stoppedMilliseconds > 10000) {
                totalTime += 9;
            }

            displayScore.setText(displayScore.getResources().getString(R.string.score).replace("0", "") + score);
            displayTime.setText(displayTime.getResources().getString(R.string.total_time).replace("0", "") + totalTime);
        }else{
            wrong.start();
        }

        if (!cities.isEmpty()) {
            startRound();
        } else {
            endGame();
        }
    }

    private void endGame() {
        Intent intent = new Intent();
        intent.setClass(rootView.getContext(), HighscoreActivity.class);
        rootView.getContext().startActivity(intent);
    }

    public void startRound() {
        round++;
        TextView displayRound = (TextView) rootView.findViewById(R.id.round);
        TextView displayCityName = (TextView) rootView.findViewById(R.id.cityName);

        for (int i = 0; i < 4; i++) {
            pickCity();
        }

        correctCity = displayCities.get(0);

        getImages();
        //displayCityName.setText(correctCity);

        DelayTask delay = new DelayTask(displayCityName);
        delay.execute();

        displayRound.setText(displayRound.getResources().getString(R.string.round) + String.valueOf(round) + "/" + (cities_size / 4));
    }

    public void loadImage(ImageButton button) throws ExecutionException, InterruptedException {
        try {
            randomDisplayCity = (int) ((Math.random() * displayCities.size()));
            builder.append(displayCities.get(randomDisplayCity));
            new DownloadURLTask(button).execute(builder.toString(), displayCitiesIndexNumbers.get(randomDisplayCity)).get();
            button.setTag(displayCities.get(randomDisplayCity));
            builder.replace(0, builder.capacity(), URL);
            builder = new StringBuilder(URL);
            displayCities.remove(randomDisplayCity);
            displayCitiesIndexNumbers.remove(randomDisplayCity);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Toast.makeText(button.getContext(), "Babom!", Toast.LENGTH_SHORT).show();
        }
    }

    public void pickCity() {
        randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);
    }
}
