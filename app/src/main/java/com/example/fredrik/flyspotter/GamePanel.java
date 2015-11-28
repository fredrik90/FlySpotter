package com.example.fredrik.flyspotter;

import android.content.Context;
import android.content.Intent;
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
    public int xpos;
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
    public int score;
    private ArrayList<Fly> flies;
    private ArrayList<Smoke> smokes;
    private Context mContext;



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

        //Start the game loop
        thread.setRunning(true);
        thread.start();

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            flies.add(new Fly(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_sprite), 32, 32, 4));
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            flies.add(new Fly(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_sprite), 32, 32, 4));
        }

        return super.onTouchEvent(event);
    }*/


    @Override
    public boolean onTouchEvent(MotionEvent e) {

            int get_xpos = (int) e.getX();
            int get_ypos = (int) e.getY();
            xpos = get_xpos;
            ypos = get_ypos;

        return true;
    }


    public void update()
    {

        //System.out.println(xpos);

        bg.update();
        cake.update();

        //Stops the score, when you are out of lives
        if (lives >= 0){
        score++;}
        timer++;

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
                smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8));
                cakeanimation1 = 0;}

        if (lives == 3 && cakeanimation2 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake3), 128, 96, 3);
            smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8));
            cakeanimation2 = 0;}

        if (lives == 2 && cakeanimation3 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake4), 128, 96, 3);
            smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8));
            cakeanimation3 = 0;}

        if (lives == 1 && cakeanimation4 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake5), 128, 96, 1);
            smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8));
            cakeanimation4 = 0;
        }

        if (lives <= 0 && cakeanimation5 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.dish), 128, 96, 1);
            smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8));
            cakeanimation5 = 0;
        }


        //Add flies on timer
        //The higher score, the more flies will come
        //Minimum of regular flies will spawn every third second.
        //Maximum of regular flies will spawn after 6 minutes, then it spawn a regular fly pr second.
        if (score < 900){spawn = 90;}
        //Resets the timer
        if (score == 899){timer = 0;}
        if (score > 900 && score < 1800){spawn = 85;}

        if (score == 1799){timer = 0;}
        if (score > 1800 && score < 2700){spawn = 80;}

        if (score == 2699){timer = 0;}
        if (score > 2700 && score < 3600){spawn = 75;}

        if (score == 3599){timer = 0;}
        if (score > 3600 && score < 4500){spawn = 70;}

        if (score == 4499){timer = 0;}
        if (score > 4500 && score < 5400){spawn = 65;}

        if (score == 5399){timer = 0;}
        if (score > 5400 && score < 6300){spawn = 60;}

        if (score == 6299){timer = 0;}
        if (score > 6300 && score < 7200){spawn = 55;}

        if (score == 7199){timer = 0;}
        if (score > 7200 && score < 8100){spawn = 50;}

        if (score == 8099){timer = 0;}
        if (score > 8100 && score < 9000){spawn = 45;}

        if (score == 8999){timer = 0;}
        if (score > 9000 && score < 9900){spawn = 40;}

        if (score == 9899){timer = 0;}
        if (score > 9900 && score < 10800){spawn = 35;}

        if (score == 10799){timer = 0;}
        if (score > 10800){spawn = 30;}

            if (timer == spawn) {
                timer = 0;
                //Create the flies!
                flies.add(new Fly(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_sprite), 32, 32, 4));
            }

        //Loop through every smoke
        for(int i = 0; i<smokes.size();i++)
        {
            smokes.get(i).update();

        }
        //Loop through every fly, check for collision and remove
        for (int i = 0; i < flies.size(); i++)
        {

            //Update the fly
            flies.get(i).update();

                //If you click on the fly, it will be squashed.
                //It only compare the fly coordinations with the ontouch coordinations.
                if (flies.get(i).x < xpos + 16 && flies.get(i).x > xpos  - 16
                        && flies.get(i).y < ypos + 16 && flies.get(i).y > ypos - 16) {
                    flies.remove(i);
                    break;
                }


           /* if (flies.get(i).x < xpos / 2.79 + 16 && flies.get(i).x > xpos / 2.79 - 16
                    && flies.get(i).y < ypos / 5.35 + 16 && flies.get(i).y > ypos / 5.35 - 16) {
                flies.remove(i);
                break; */


            if(collision(flies.get(i), cake))
            {
                //Remove the flies, when colliding with the cake!
                flies.remove(i);
                lives-=1;
                break;
            }
        }




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