package com.example.fredrik.flyspotter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fredrik on 04.12.2015.
 */
public class HighScores extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get highscore
        Highscore highscores = new Highscore(this);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.highscore);


        //Show your Highscore list!
        if (highscores.getDate(0) != ""){
        TextView s1 = (TextView) findViewById(R.id.score1);
        s1.setText(String.valueOf("1. " + highscores.getScore(0) + "  -  " + highscores.getDate(0)));
        s1.setTextColor(Color.parseColor("#CCFF33"));
        }

        if (highscores.getDate(1) != "") {
            TextView s2 = (TextView) findViewById(R.id.score2);
            s2.setText(String.valueOf("2. " + highscores.getScore(1) + "  -  " + highscores.getDate(1)));
            s2.setTextColor(Color.parseColor("#A0A0A0"));
        }

        if (highscores.getDate(2) != "") {
            TextView s3 = (TextView) findViewById(R.id.score3);
            s3.setText(String.valueOf("3. " + highscores.getScore(2) + "  -  " + highscores.getDate(2)));
            s3.setTextColor(Color.parseColor("#993300"));
        }

        if (highscores.getDate(3) != "") {
            TextView s4 = (TextView) findViewById(R.id.score4);
            s4.setText(String.valueOf("4. " + highscores.getScore(3) + "  -  " + highscores.getDate(3)));
            s4.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(4) != "") {
            TextView s5 = (TextView) findViewById(R.id.score5);
            s5.setText(String.valueOf("5. " + highscores.getScore(4) + "  -  " + highscores.getDate(4)));
            s5.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(5) != "") {
            TextView s6 = (TextView) findViewById(R.id.score6);
            s6.setText(String.valueOf("6. " + highscores.getScore(5) + "  -  " + highscores.getDate(5)));
            s6.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(6) != "") {
            TextView s7 = (TextView) findViewById(R.id.score7);
            s7.setText(String.valueOf("7. " + highscores.getScore(6) + "  -  " + highscores.getDate(6)));
            s7.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(7) != "") {
            TextView s8 = (TextView) findViewById(R.id.score8);
            s8.setText(String.valueOf("8. " + highscores.getScore(7) + "  -  " + highscores.getDate(7)));
            s8.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(8) != "") {
            TextView s9 = (TextView) findViewById(R.id.score9);
            s9.setText(String.valueOf("9. " + highscores.getScore(8) + "  -  " + highscores.getDate(8)));
            s9.setTextColor(Color.parseColor("#0000FF"));
        }

        if (highscores.getDate(9) != "") {
            TextView s10 = (TextView) findViewById(R.id.score10);
            s10.setText(String.valueOf("10. " + highscores.getScore(9) + "  -  " + highscores.getDate(9)));
            s10.setTextColor(Color.parseColor("#0000FF"));
        }


    }

    //Back, closing the activity!
    public void ClickBack(View view) {
        finish();
    }

}
