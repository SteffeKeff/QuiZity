package se.keff.android.quizity.game;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import se.keff.android.quizity.highscore.HighscoreActivity;
import se.keff.android.quizity.R;

public final class Game {
    private final View rootView;
    private static final String URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&q=";
    private StringBuilder builder = new StringBuilder(URL);
    private final ArrayList<String> cities;
    private final ArrayList<String> citiesIndexNumbers;
    private final ArrayList<String> displayCities;
    private final ArrayList<String> displayCitiesIndexNumbers;

    private String correctCity;
    private int cities_size;
    private int round = 0;
    private int randomDisplayCity;
    private int randomCity;
    public static int score;
    public static int totalTime;

    public Game(View rootView) {
        this.rootView = rootView;
        String[] citiesArray = rootView.getResources().getStringArray(R.array.cities);
        String[] citiesImageNumbersArray = rootView.getResources().getStringArray(R.array.citiesImageNumber);
        cities = new ArrayList(Arrays.asList(citiesArray));
        citiesIndexNumbers = new ArrayList(Arrays.asList(citiesImageNumbersArray));
        displayCities = new ArrayList<>();
        displayCitiesIndexNumbers = new ArrayList<>();

        cities_size = cities.size();
    }

    public void getImages() {
        ArrayList<ImageButton> buttons = new ArrayList<>();
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton1));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton2));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton3));
        buttons.add((ImageButton) rootView.findViewById(R.id.imageButton4));

        randomDisplayCity = (int) ((Math.random() * displayCities.size()));
        try {
            for (ImageButton button : buttons) {
                loadImage(button);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void nextRound(ImageButton buttonClicked, int stoppedMilliseconds) {
        if (correctCity == buttonClicked.getTag()) {
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

        displayCityName.setText(correctCity);
        displayRound.setText(displayRound.getResources().getString(R.string.round) + String.valueOf(round) + "/" + (cities_size / 4));

        getImages();
    }

    public void loadImage(ImageButton button) throws ExecutionException, InterruptedException {
        randomDisplayCity = (int) ((Math.random() * displayCities.size()));
        builder.append(displayCities.get(randomDisplayCity));
        new DownloadURLTask(button).execute(builder.toString(), displayCitiesIndexNumbers.get(randomDisplayCity)).get();
        button.setTag(displayCities.get(randomDisplayCity));
        builder.replace(0, builder.capacity(), URL);
        displayCities.remove(randomDisplayCity);
        displayCitiesIndexNumbers.remove(randomDisplayCity);
    }

    public void pickCity() {
        randomCity = (int) ((Math.random() * cities.size()));
        displayCities.add(cities.get(randomCity));
        displayCitiesIndexNumbers.add(citiesIndexNumbers.get(randomCity));
        cities.remove(randomCity);
        citiesIndexNumbers.remove(randomCity);
    }
}