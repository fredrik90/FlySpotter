package com.example.fredrik.flyspotter;

import android.content.Context;
import android.content.SharedPreferences;

//This code is reused from: http://osdir.com/ml/Android-Developers/2010-01/msg00794.html

public class Highscore {
    private SharedPreferences preferences;
    private String dates[];
    private long score[];

    public Highscore(Context context)
    {
        //Get preference, it can only be called in this app!
        preferences = context.getSharedPreferences("Highscore", Context.MODE_PRIVATE);

        dates = new String[10];
        score = new long[10];

        for (int x = 0; x < 10; x++)
        {
            dates[x] = preferences.getString("date"+x, "");
            score[x] = preferences.getLong("score" + x, 0);
        }

    }

    public String getDate(int x)
    {
        //get the name of the x-th position in the Highscore-List
        return dates[x];
    }

    public long getScore(int x)
    {
        //get the score of the x-th position in the Highscore-List
        return score[x];
    }


    public boolean addScore(String date, long score) {
        //add the score with the name to the Highscore-List
        int position;
        for (position = 0; position < 10 && this.score[position] > score;
             position++);



            if (position == 10) return false;

            for (int x = 9; x > position; x--) {
                dates[x] = dates[x - 1];
                this.score[x] = this.score[x - 1];
            }

            this.dates[position] = new String(date);
            this.score[position] = score;

            SharedPreferences.Editor editor = preferences.edit();
            for (int x = 0; x < 10; x++) {
                editor.putString("date" + x, this.dates[x]);
                editor.putLong("score" + x, this.score[x]);
            }

            editor.commit();
            return true;


    }

}
