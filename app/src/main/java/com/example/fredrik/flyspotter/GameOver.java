package com.example.fredrik.flyspotter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class GameOver extends Activity {

    private MediaPlayer intromusic;



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
        String ScoreString = Long.toString(scoreValue);
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

        //Give feedback if you beat one of your scores!
        if (scoreValue > highscores.getScore(9))
        {
            Toast.makeText(getApplicationContext(), "Congratulations, your score has reached top 10!", Toast.LENGTH_LONG).show();
        }



        //Show the latest score!
        TextView t = (TextView) findViewById(R.id.getScore);
        t.setText(String.valueOf(scoreValue));
        t.setTextColor(Color.parseColor("#0000FF"));
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


    //Exit Game
    public void ClickExitGame(View view) {
        System.exit(0);
    }
}
