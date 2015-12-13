package com.example.fredrik.flyspotter;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Achievements extends Activity {

    //Set variables
    String Achievement1;
    String Achievement2;
    String Achievement3;
    String Achievement4;
    String Achievement5;
    String Achievement6;
    String Achievement7;
    String Achievement8;
    int Counter = 0;
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
        AchievementDB achievement5 = dbHandler.findachievement("You played for more than 6 minutes!");
        AchievementDB achievement6 = dbHandler.findachievement("You played for more than 10 minutes!");
        AchievementDB achievement7 = dbHandler.findachievement("You swatted 10 or more bugs really fast!");
        AchievementDB achievement8 = dbHandler.findachievement("You swatted 10 or more bugs near the cake!");

        //Display achievements if exists!
        if (achievement != null) {
            Counter++;
            Achievement1 = achievement.getName();
        }else {Achievement1 = "Not unlocked!";}

        if (achievement2 != null) {
            Counter++;
            Achievement2 = achievement2.getName();
        }else {Achievement2 = "Not unlocked!";}

        if (achievement3 != null) {
            Counter++;
            Achievement3 = achievement3.getName();
        }else {Achievement3 = "Not unlocked!";}

        if (achievement4 != null) {
            Counter++;
            Achievement4 = achievement4.getName();
         }else {Achievement4 = "Not unlocked!";}

        if (achievement5 != null) {
            Counter++;
            Achievement5 = achievement5.getName();
        }else {Achievement5 = "Not unlocked!";}

        if (achievement6 != null) {
            Counter++;
            Achievement6 = achievement6.getName();
        }else {Achievement6 = "Not unlocked!";}

        if (achievement7 != null) {
            Counter++;
            Achievement7 = achievement7.getName();
        }else {Achievement7 = "Not unlocked!";}

        if (achievement8 != null) {
            Counter++;
            Achievement8 = achievement8.getName();
        }else {Achievement8 = "Not unlocked!";}


        //Display achievements in a textview!
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

        TextView t5 = (TextView) findViewById(R.id.getAchievement5);
        t5.setText(String.valueOf(Achievement5));
        if (achievement5 != null) {
            t5.setTextColor(Color.parseColor("#0000FF"));}else{t5.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t6 = (TextView) findViewById(R.id.getAchievement6);
        t6.setText(String.valueOf(Achievement6));
        if (achievement6 != null) {
            t6.setTextColor(Color.parseColor("#0000FF"));}else{t6.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t7 = (TextView) findViewById(R.id.getAchievement7);
        t7.setText(String.valueOf(Achievement7));
        if (achievement7 != null) {
            t7.setTextColor(Color.parseColor("#0000FF"));}else{t7.setTextColor(Color.parseColor("#C0C0C0"));}

        TextView t8 = (TextView) findViewById(R.id.getAchievement8);
        t8.setText(String.valueOf(Achievement8));
        if (achievement8 != null) {
            t8.setTextColor(Color.parseColor("#0000FF"));}else{t8.setTextColor(Color.parseColor("#C0C0C0"));}

        //Display achievement counter
        String Counterstring = String.valueOf(Counter);
        TextView ac = (TextView) findViewById(R.id.achievementsCounter);
        ac.setTextColor(Color.parseColor("#0000FF"));
        ac.setText(String.valueOf(Counterstring + "/8"));

    }

    //Back, closing the activity!
    public void ClickBack(View view) {
        finish();
    }

}
