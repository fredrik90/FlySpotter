package com.example.fredrik.flyspotter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.IntentCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Fredrik on 23.11.2015.
 */


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 384;
    public static final int HEIGHT = 430;
    public double xpos;
    public int ypos;
    public int drawboxLenght;
    public int spawn;
    public int timer;
    private MainThread thread;
    private Background bg;
    private Cake cake;
    private int lives;
    private int cakeanimation1;
    private int cakeanimation2;
    private int cakeanimation3;
    private int cakeanimation4;
    private int cakeanimation5;
    boolean touch;
    public int score;
    public int time;
    private ArrayList<Fly> flies;
    private ArrayList<Smoke> smokes;
    private ArrayList<Squash> squash;
    private Context mContext;
    int Checkwidth = this.getResources().getDisplayMetrics().widthPixels;
    int Checkheight = this.getResources().getDisplayMetrics().heightPixels;



    //Constructor
    public GamePanel (Context context) {

        super(context);

        this.mContext = context;



        //Add the callback to the surface holder to intercept events
        getHolder().addCallback(this);


        //make gamePanel focusable so it can handle events
        setFocusable(true);


    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        int counter = 0;
        while(retry && counter < 1000)
        {
            counter++;
            try{ thread.setRunning(false);
                thread.join();
                retry = false;
            }catch(InterruptedException e){e.printStackTrace();}


        }

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this);

        score = 0;
        drawboxLenght = 280;
        lives = 5;
        spawn = 90;
        timer = 0;

        cakeanimation1 = 1;
        cakeanimation2 = 1;
        cakeanimation3 = 1;
        cakeanimation4 = 1;
        cakeanimation5 = 1;

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background1));
        cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake), 128, 96, 3);
        flies = new ArrayList<>();
        smokes = new ArrayList<>();
        squash = new ArrayList<>();

        //Start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    //Click on enemies.
    public boolean onTouchEvent(MotionEvent e) {

        final int action = e.getAction();
        //Must tap on the enemies!
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                //Get the x and y coordinate of where you touched on the screen!
                int get_xpos = (int) e.getX();
                int get_ypos = (int) e.getY();
                //This will get a new x and y coordinate, so its the same as the
                //gamepanel's, that is 384 X 430, and not get the x and y coordinate
                //of the resolution of the screen!
                xpos = get_xpos * WIDTH / Checkwidth;
                ypos = get_ypos * HEIGHT / Checkheight;

                //touch must be true if you want to squash the enemies!
                //Without this you can tap on one place, and if the enemies
                //run into that place later, they will be squashed!
                touch = true;

                break;
            }
            //This prevent the user from moving the finger over enemies!
            case MotionEvent.ACTION_MOVE: {
                xpos = -100;
                ypos = -100;
                break;
            }
        }
        return true;
    }

    public void update()
    {

        System.out.println(touch);

        bg.update();
        cake.update();


        timer++;
        time++;

        if (lives <= 0) {
            //Game over, starts the game over activity
            Intent intent = new Intent(mContext, GameOver.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("Score", score);


            mContext.startActivity(intent);
        }

        //Make the cake change, depending on your lives!
        //And add smoke
        if (lives == 4 && cakeanimation1 == 1){
                cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake2), 128, 96, 3);
                cakeanimation1 = 0;}

        if (lives == 3 && cakeanimation2 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake3), 128, 96, 3);
            cakeanimation2 = 0;}

        if (lives == 2 && cakeanimation3 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake4), 128, 96, 3);
            cakeanimation3 = 0;}

        if (lives == 1 && cakeanimation4 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake5), 128, 96, 1);
            cakeanimation4 = 0;
        }

        if (lives <= 0 && cakeanimation5 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.dish), 128, 96, 1);
            cakeanimation5 = 0;
        }


        //Add flies on timer
        //The higher score, the more flies will come
        //Minimum of regular flies will spawn every third second.
        //Maximum of regular flies will spawn after 6 minutes, then it spawn a regular fly pr second.
        if (time < 900){spawn = 90;}
        //Resets the timer
        if (time == 899){timer = 0;}
        if (time > 900 && time < 1800){spawn = 85;}

        if (time == 1799){timer = 0;}
        if (time > 1800 && time < 2700){spawn = 80;}

        if (time == 2699){timer = 0;}
        if (time > 2700 && time < 3600){spawn = 75;}

        if (time == 3599){timer = 0;}
        if (time > 3600 && time < 4500){spawn = 70;}

        if (time == 4499){timer = 0;}
        if (time > 4500 && time < 5400){spawn = 65;}

        if (time == 5399){timer = 0;}
        if (time > 5400 && time < 6300){spawn = 60;}

        if (time == 6299){timer = 0;}
        if (time > 6300 && time < 7200){spawn = 55;}

        if (time == 7199){timer = 0;}
        if (time > 7200 && time < 8100){spawn = 50;}

        if (time == 8099){timer = 0;}
        if (time > 8100 && time < 9000){spawn = 45;}

        if (time == 8999){timer = 0;}
        if (time > 9000 && time < 9900){spawn = 40;}

        if (time == 9899){timer = 0;}
        if (time > 9900 && time < 10800){spawn = 35;}

        if (time == 10799){timer = 0;}
        if (time > 10800){spawn = 30;}

            if (timer == spawn) {
                timer = 0;
                //Create the flies!
                flies.add(new Fly(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_sprite), 32, 32, 4));
            }

        //Loop through every smoke
        for(int i = 0; i<smokes.size();i++)
        {
            smokes.get(i).update();
            //removes after animation
            if (smokes.get(i).x < -99){smokes.remove(i);}

        }

        //Loop through every squash
        for(int i = 0; i<squash.size();i++)
        {
            squash.get(i).update();
            //removes after animation
            if (squash.get(i).x < -99){squash.remove(i);}
        }

        //Loop through every fly, check for collision and remove
        for (int i = 0; i < flies.size(); i++)
        {

            //Update the fly
            flies.get(i).update();

                //If you click on the fly, it will be squashed.
                //It only compare the fly coordination with the ontouch coordination.
            if (touch == true) {
                if (flies.get(i).x < xpos + 8 && flies.get(i).x > xpos - 32
                        && flies.get(i).y < ypos + 8 && flies.get(i).y > ypos - 32) {
                    squash.add(new Squash(BitmapFactory.decodeResource(getResources(), R.drawable.squash), 32, 32, 8, flies.get(i).x, flies.get(i).y));
                    flies.remove(i);
                    score += 10;
                    break;
                }
            }


            if(collision(flies.get(i), cake))
            {
                //Remove the flies, when colliding with the cake!
                smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8, flies.get(i).x - 16, flies.get(i).y));
                flies.remove(i);
                lives-=1;
                break;
            }
        }

    touch = false;
    }

    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }



    @Override
    public void draw(Canvas canvas)
    {

        //Scale the background
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null)
        {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            cake.draw(canvas);

            //draw flies
            for(Fly f: flies)
            {
                f.draw(canvas);
            }

            //draw smokes
            for(Smoke s: smokes)
            {
                s.draw(canvas);
            }

            //draw squash
            for(Squash sq: squash)
            {
                sq.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }


    }

    //Draw the score
    public void drawText(Canvas canvas) {
        Paint paint = new Paint();

        //Set the box length, the higher score, the longer the box will become.
        if (score > 9 && score < 100){drawboxLenght = 310;}
        if (score > 99 && score < 1000){drawboxLenght = 340;}
        if (score > 999 && score < 10000){drawboxLenght = 370;}
        if (score > 9999 && score < 100000){drawboxLenght = 400;}
        if (score > 99999 && score < 1000000){drawboxLenght = 430;}
        if (score > 999999 && score < 10000000){drawboxLenght = 460;}

        //Draw box lines
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawRect(0, 0, drawboxLenght + 5, 140, paint);
        //Draw the box
        paint.setColor(Color.rgb(40, 40, 250));
        paint.setStrokeWidth(10);
        canvas.drawRect(5, 5, drawboxLenght, 135, paint);
        //Draw score
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Score: " + (score), 30, HEIGHT - 340, paint);
    }



}