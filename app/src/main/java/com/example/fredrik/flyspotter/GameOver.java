package com.example.fredrik.flyspotter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class GameOver extends Activity {

    private MediaPlayer intromusic;

    AchievementDBHandler dbHandler = new AchievementDBHandler(this, null, null, 1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Play background music, music found at www.freesound.org
        intromusic = MediaPlayer.create(GameOver.this, R.raw.introfly);
        intromusic.setLooping(true);
        intromusic.start();


        //Get the latest score!
        Intent intent = getIntent();
        long scoreValue = intent.getIntExtra("Score", 0);
        //Get the date!
        DateFormat dateForm = new SimpleDateFormat("dd MMM yy");
        String dateOutput = dateForm.format(new Date());

        //Input highscore
        Highscore highscores = new Highscore(this);
        highscores.addScore(dateOutput, scoreValue);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.game_over);


        //Show the latest score!
        TextView t = (TextView) findViewById(R.id.getScore);
        t.setText(String.valueOf(scoreValue));
        t.setTextColor(Color.parseColor("#0000FF"));



        //Unlock achievements

        //Get this achievement the first time you finish the game!
        //Checks if a achievement exists.
        AchievementDB Checkachievement = dbHandler.findachievement("You played the game!");
        if (Checkachievement == null) {
            //Add achievement if it does not exist!
        AchievementDB achievement = new AchievementDB("You played the game!");
        dbHandler.addAchievement(achievement);}

        //Get this achievement if you get over 100 points!
        //Checks if a achievement exists.
        AchievementDB Checkachievement2 = dbHandler.findachievement("You have reached over 100 points!");
        if (scoreValue > 100){
        if (Checkachievement2 == null) {
            //Add achievement if it does not exist!
            AchievementDB achievement2 = new AchievementDB("You have reached over 100 points!");
            dbHandler.addAchievement(achievement2);}}

        //Get this achievement if you get over 1000 points!
        //Checks if a achievement exists.
        AchievementDB Checkachievement3 = dbHandler.findachievement("You have reached over 1000 points!");
        if (scoreValue > 1000){
            if (Checkachievement3 == null) {
                //Add achievement if it does not exist!
                AchievementDB achievement3 = new AchievementDB("You have reached over 1000 points!");
                dbHandler.addAchievement(achievement3);}}

        //Get this achievement if you get over 9000 points!
        //Checks if a achievement exists.
        AchievementDB Checkachievement4 = dbHandler.findachievement("It's over 9000!!!");
        if (scoreValue > 9000){
            if (Checkachievement4 == null) {
                //Add achievement if it does not exist!
                AchievementDB achievement4 = new AchievementDB("It's over 9000!!!");
                dbHandler.addAchievement(achievement4);}}





        //Give feedback if you beat one of your scores!
        if (scoreValue > highscores.getScore(9))
        {
            Toast.makeText(getApplicationContext(), "Congratulations, your score has reached top 10!", Toast.LENGTH_LONG).show();
        }


    }




    //Retry game
    public void ClickStartGame(View view)
    {
        //Stops intro music
        intromusic.stop();
        //Sets to gamepanel!
        setContentView(new GamePanel(this));
    }

    //Highscore
    public void ClickHighScore(View view) {
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
    }

    //Achievements
    public void ClickAchievement(View view) {
        //Open Achievement Activity
        Intent intent = new Intent(this, Achievements.class);
        startActivity(intent);
    }


    //Exit Game
    public void ClickExitGame(View view) {
        System.exit(0);
    }
}
