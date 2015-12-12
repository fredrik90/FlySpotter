package com.example.fredrik.flyspotter;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Achievements extends Activity {

    String Achievement1;
    String Achievement2;
    String Achievement3;
    String Achievement4;
    AchievementDBHandler dbHandler = new AchievementDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.achievements);

        //Checks if achievements exists!
        AchievementDB achievement = dbHandler.findachievement("You played the game!");
        AchievementDB achievement2 = dbHandler.findachievement("You have reached over 100 points!");
        AchievementDB achievement3 = dbHandler.findachievement("You have reached over 1000 points!");
        AchievementDB achievement4 = dbHandler.findachievement("It's over 9000!!!");

        //Display achievements if exists!
        if (achievement != null) {

            Achievement1 = achievement.getName();
        }else {Achievement1 = "Not unlocked!";}

        if (achievement2 != null) {

            Achievement2 = achievement2.getName();
        }else {Achievement2 = "Not unlocked!";}

        if (achievement3 != null) {

            Achievement3 = achievement3.getName();
        }else {Achievement3 = "Not unlocked!";}

        if (achievement4 != null) {
        Achievement4 = achievement4.getName();
         }else {Achievement4 = "Not unlocked!";}


        //Display achievements in a string!
        TextView t = (TextView) findViewById(R.id.getAchievement1);
        t.setText(String.valueOf(Achievement1));
        if (achievement != null) {
            t.setTextColor(Color.parseColor("#0000FF"));}else{t.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t2 = (TextView) findViewById(R.id.getAchievement2);
        t2.setText(String.valueOf(Achievement2));
        if (achievement2 != null) {
            t2.setTextColor(Color.parseColor("#0000FF"));}else{t2.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t3 = (TextView) findViewById(R.id.getAchievement3);
        t3.setText(String.valueOf(Achievement3));
        if (achievement3 != null) {
            t3.setTextColor(Color.parseColor("#0000FF"));}else{t3.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t4 = (TextView) findViewById(R.id.getAchievement4);
        t4.setText(String.valueOf(Achievement4));
        if (achievement4 != null) {
        t4.setTextColor(Color.parseColor("#0000FF"));}else{t4.setTextColor(Color.parseColor("#C0C0C0"));}

    }

    //Back, closing the activity!
    public void ClickBack(View view) {
        finish();
    }

}
