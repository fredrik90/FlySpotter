package com.example.fredrik.flyspotter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fredrik on 04.12.2015.
 */
public class HighScores extends GameOver {
    public static final String PREFS_NAME = "FlySpotterPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String value = prefs.getString("HighScore", "");

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.highscore);

        //Show the latest score!
        TextView t = (TextView) findViewById(R.id.score1);
        t.setText(String.valueOf(value));
        t.setTextColor(Color.parseColor("#0000FF"));

    }

}
