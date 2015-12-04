package com.example.fredrik.flyspotter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fredrik on 27.11.2015.
 */


public class GameOver extends Activity {

    //SharedPreferences prefs = this.getSharedPreferences("FlySpotterPref", Context.MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the latest score!
        Intent intent = getIntent();
        int scoreValue = intent.getIntExtra("Score", 0);

       /* SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("HighScore", scoreValue);
        editor.commit(); */

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.game_over);

        //Show the latest score!
        TextView t = (TextView) findViewById(R.id.getScore);
        t.setText(String.valueOf(scoreValue));
        t.setTextColor(Color.parseColor("#0000FF"));
    }


    //Start Game
    public void ClickStartGame(View view) {
        setContentView(new GamePanel(this));
    }

    //Highscore
    public void ClickHighScore(View view) {
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
    }


    //Exit Game
    public void ClickExitGame(View view) {
        System.exit(0);
    }
}
