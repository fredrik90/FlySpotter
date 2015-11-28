package com.example.fredrik.flyspotter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fredrik on 27.11.2015.
 */


public class GameOver extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String scoreValue = getIntent().getExtras().getString("Score");

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.game_over);
        System.out.println(scoreValue);

        TextView t = (TextView) findViewById(R.id.getScore);
        t.setText(String.valueOf(scoreValue));
    }


    //Start Game
    public void ClickStartGame(View view) {
        setContentView(new GamePanel(this));
    }
    //Exit Game
    public void ClickExitGame(View view) {
        System.exit(0);
    }
}
