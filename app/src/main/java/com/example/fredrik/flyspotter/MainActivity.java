package com.example.fredrik.flyspotter;
//The main functions in GamePanel, MainThread, Background, Cake, GameObject, Animation is from a youtube tutorial:
// https://www.youtube.com/watch?v=-XOMJYZmfkw

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaPlayer;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //sets up intro music
        MediaPlayer intromusic;
        intromusic = MediaPlayer.create(MainActivity.this, R.raw.introfly);
        intromusic.setLooping(true);
        intromusic.start();

    }
    //Start Game
    public void ClickStartGame(View view) {
        setContentView(new GamePanel(this));

    //startActivity(new Intent(getApplicationContext(), GameOver.class));
    // sets up background music
        MediaPlayer backgroundmusic;
        backgroundmusic = MediaPlayer.create(MainActivity.this, R.raw.background);
        backgroundmusic.setLooping(true);
        backgroundmusic.start();


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
