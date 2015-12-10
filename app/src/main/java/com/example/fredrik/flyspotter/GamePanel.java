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
import android.media.MediaPlayer;
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
    private Background2 bg2;
    private Background2 bg3;
    private Background2 bg4;
    private Cake cake;
    private int lives;
    private int cakeanimation1;
    private int cakeanimation2;
    private int cakeanimation3;
    private int cakeanimation4;
    private int cakeanimation5;
    int flyswattermove = 0;
    int swatrester = 10;
    int setswatrester = 10;
    boolean flyswatterSwat;
    boolean flyswatterSwitch = false;
    public int score;
    public int time;
    public int swatswitch = 0;
    public int gameovertimer = 0;
    private ArrayList<Fly> flies;
    private ArrayList<Bumblebee> bumblebee;
    private ArrayList<Mosquito> mosquito;
    private ArrayList<Smoke> smokes;
    private ArrayList<Squash> squash;
    private ArrayList<Flyswatter> flyswatter;
    private ArrayList<ShowScore> showscore;
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

        //Sky background
        bg3 = new Background2(BitmapFactory.decodeResource(getResources(), R.drawable.background3));
        //Cloud background
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background1));
        //Background move speed
        bg.setVector(-1);
        //Second background
        bg2 = new Background2(BitmapFactory.decodeResource(getResources(), R.drawable.background2));
        //glass in windows
        bg4 = new Background2(BitmapFactory.decodeResource(getResources(), R.drawable.glass));
        cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake), 128, 87, 4);
        flies = new ArrayList<>();
        bumblebee = new ArrayList<>();
        mosquito = new ArrayList<>();
        smokes = new ArrayList<>();
        squash = new ArrayList<>();
        flyswatter = new ArrayList<>();
        showscore = new ArrayList<>();
        flyswatter.add(new Flyswatter(BitmapFactory.decodeResource(getResources(), R.drawable.fly_swatter), 96, 512, 1, 256, 334));


        //Start the game loop
        thread.setRunning(true);
        thread.start();



    }

    @Override

    public boolean onTouchEvent(MotionEvent e) {

        final int action = e.getAction();
        //Must tap!
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

                //This will make you wait for the flyswatter, so you only can swat again after the flyswatter are back on its original place!
                //And you must wait for the swatrester, when you swat to a close position, it goes very fast! The swatrester prevents the user
                //to spam the cake very fast, and never loose the game! And how higher the setswatrester is, how longer you have to wait until
                //you can swat again, so if you swat near the cake the rest time is greater!
                if (swatrester >= setswatrester){
                if (flyswatterSwitch == false){
                    flyswattermove = 1;
                    flyswatterSwitch = true;
                    swatrester = 0;
                    if (ypos < 320){setswatrester = 10;}
                    if (ypos > 320){setswatrester = 18;}
                }
                }

                break;
            }

        }
        return true;
    }


    public void update()
    {

        bg.update();
        cake.update();


        timer++;
        time++;

        if (lives <= 0) {
            gameovertimer++;
        }


        if (gameovertimer == 40) {
            //Game over, starts the game over activity
            Intent intent = new Intent(mContext, GameOver.class);
            intent.putExtra("Score", score);
            //Close this activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
        }

        //Make the cake change, depending on your lives!
        //And add smoke
        if (lives == 4 && cakeanimation1 == 1){
                cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake2), 128, 87, 4);
                cakeanimation1 = 0;}

        if (lives == 3 && cakeanimation2 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake3), 128, 87, 1);
            cakeanimation2 = 0;}

        if (lives == 2 && cakeanimation3 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake4), 128, 87, 1);
            cakeanimation3 = 0;}

        if (lives == 1 && cakeanimation4 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.cake5), 128, 87, 1);
            cakeanimation4 = 0;
        }

        if (lives <= 0 && cakeanimation5 == 1){
            cake = new Cake(BitmapFactory.decodeResource(getResources(), R.drawable.dish), 128, 87, 1);
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

        //1800 = 1 minute!
            if (timer == spawn) {
                timer = 0;
                //Create the flies!
                flies.add(new Fly(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_sprite), 48, 32, 3));

                //Adds mosquitoes after two minutes!
                if (time > 900){mosquito.add(new Mosquito(BitmapFactory.decodeResource(getResources(), R.drawable.mosquito), 24, 24, 2));}

                //Adds bumblebees after one minute!
                if (time > 1350){bumblebee.add(new Bumblebee(BitmapFactory.decodeResource(getResources(), R.drawable.bumblebee), 48, 48, 2));}

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
            if (squash.get(i).y > 500){squash.remove(i);}
        }

        //Loop through every showscore
        for(int i = 0; i<showscore.size();i++)
        {
            showscore.get(i).update();
            //removes after animation
            if (showscore.get(i).y < -32){showscore.remove(i);}
        }

        //Loop through every fly, check for collision and remove
        for (int i = 0; i < flies.size(); i++)
        {

            //Update the fly
            flies.get(i).update();

            //It only compare the fly coordination with the flyswatter coordination.
            //When they collide, the fly will be squashed

                if (flies.get(i).x < flyswatter.get(0).x + 64 && flies.get(i).x > flyswatter.get(0).x - 64
                        && flies.get(i).y < flyswatter.get(0).y + 64 && flies.get(i).y > flyswatter.get(0).y - 64 && flyswatterSwat == true) {
                    squash.add(new Squash(BitmapFactory.decodeResource(getResources(), R.drawable.fly1_squash), 48, 32, 1, flies.get(i).x, flies.get(i).y));
                    showscore.add(new ShowScore(BitmapFactory.decodeResource(getResources(), R.drawable.score10), 48, 48, 20, flies.get(i).x, flies.get(i).y));
                    flies.remove(i);
                    score += 10;
                    break;
                }


            if(collision(flies.get(i), cake) && flies.get(i).y > cake.y+24)
            {
                //Remove the flies, when colliding with the cake!
                smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8, flies.get(i).x - 16, flies.get(i).y));
                flies.remove(i);
                lives -= 1;
                flyswatterSwat = false;
                break;
            }
        }

        //Loop through every bumblebee, check for collision and remove
        for (int i = 0; i < bumblebee.size(); i++)
        {

            //Update the bumblebee
            bumblebee.get(i).update();

            //It only compare the bumblebee coordination with the flyswatter coordination.
            //When they collide, the bumblebee will be squashed
                if (bumblebee.get(i).x < flyswatter.get(0).x + 64 && bumblebee.get(i).x > flyswatter.get(0).x - 64
                        && bumblebee.get(i).y < flyswatter.get(0).y + 64 && bumblebee.get(i).y > flyswatter.get(0).y - 64  && flyswatterSwat == true) {


                    bumblebee.get(i).lives -= 1;

                    if (bumblebee.get(i).lives <= 0) {
                        squash.add(new Squash(BitmapFactory.decodeResource(getResources(), R.drawable.bumblebee_squash), 48, 48, 1, bumblebee.get(i).x, bumblebee.get(i).y));
                        showscore.add(new ShowScore(BitmapFactory.decodeResource(getResources(), R.drawable.score20), 48, 48, 20, bumblebee.get(i).x, bumblebee.get(i).y));
                        bumblebee.remove(i);
                        score += 20;
                        break;
                    }
                    flyswatterSwat = false;
                }


            if(collision(bumblebee.get(i), cake) && bumblebee.get(i).y > cake.y+24)
            {
                //Remove the flies, when colliding with the cake!
                smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8, bumblebee.get(i).x - 16, bumblebee.get(i).y));
                bumblebee.remove(i);
                lives-=1;
                break;
            }
        }

        //Loop through every mosquito, check for collision and remove
        for (int i = 0; i < mosquito.size(); i++)
        {

            //Update the mosquito
            mosquito.get(i).update();

            //It only compare the mosquito coordination with the flyswatter coordination.
            //When they collide, the mosquito will be squashed

            if (mosquito.get(i).x < flyswatter.get(0).x + 64 && mosquito.get(i).x > flyswatter.get(0).x - 64
                    && mosquito.get(i).y < flyswatter.get(0).y + 64 && mosquito.get(i).y > flyswatter.get(0).y - 64 && flyswatterSwat == true) {
                squash.add(new Squash(BitmapFactory.decodeResource(getResources(), R.drawable.mosquito_squash), 24, 24, 1, mosquito.get(i).x, mosquito.get(i).y));
                showscore.add(new ShowScore(BitmapFactory.decodeResource(getResources(), R.drawable.score15), 48, 48, 20, mosquito.get(i).x, mosquito.get(i).y));
                mosquito.remove(i);
                score += 15;
                break;
            }


            if(collision(mosquito.get(i), cake) && mosquito.get(i).y > cake.y+24)
            {
                //Remove the flies, when colliding with the cake!
                smokes.add(new Smoke(BitmapFactory.decodeResource(getResources(), R.drawable.smoke), 64, 64, 8, mosquito.get(i).x - 16, mosquito.get(i).y));
                mosquito.remove(i);
                lives -= 1;
                flyswatterSwat = false;
                break;
            }
        }
        //Make it so you can swat again!
        if (swatrester < setswatrester){swatrester++;}

        //Make the flyswatter disappear when you loose all your lives!
            if (lives <= 0){
                flyswattermove = 0;
                flyswatter.get(0).y += 10;
            }

            if (lives >= 1) {

                //Sets up the ontouch position, and the original position
                double theta = Math.atan2(ypos - flyswatter.get(0).y, xpos - flyswatter.get(0).x);
                double theta2 = Math.atan2(334 - flyswatter.get(0).y, 256 - flyswatter.get(0).x);
                //Update the flyswatter
                flyswatter.get(0).update();

                //When the flyswatter reach the ontouch position, it will go back to its original place!
                if (flyswatter.get(0).x < xpos + 21 && flyswatter.get(0).x > xpos - 21
                        && flyswatter.get(0).y < ypos + 21 && flyswatter.get(0).y > ypos - 21 && flyswattermove == 1) {
                    flyswattermove = 0;
                    //The enemies can only be squashed when this variable is true, it will prevent
                    //the flyswatter to swat the enemies all the time when it go through them, this
                    //gives the illusion that you actually swat them!
                    flyswatterSwat = true;
                    swatswitch = 0;
                }
                //Sets the flyswatter to a given place, when its nearby, and on its way back!
                if (flyswatter.get(0).x < 354 && flyswatter.get(0).x > 236
                        && flyswatter.get(0).y < 354 && flyswatter.get(0).y > 314 && flyswattermove == 0) {
                    flyswatter.get(0).x = 256;
                    flyswatter.get(0).y = 334;
                    flyswattermove = 2;
                    flyswatterSwitch = false;
                }
                //Move the flyswatter to ontouch position
                if (flyswattermove == 1) {
                    flyswatter.get(0).x += 40 * Math.cos(theta);
                    flyswatter.get(0).y += 40 * Math.sin(theta);
                }

                //Move the flyswatter back!
                if (flyswattermove == 0) {
                    flyswatter.get(0).x += 40 * Math.cos(theta2);
                    flyswatter.get(0).y += 40 * Math.sin(theta2);

                    if (swatswitch >= 1) {
                        flyswatterSwat = false;
                    }

                }

                swatswitch++;
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
            bg3.draw(canvas);
            bg.draw(canvas);
            bg2.draw(canvas);
            bg4.draw(canvas);
            cake.draw(canvas);

            //draw flies
            for(Fly f: flies)
            {
                canvas.save();
                //Rotate sprite
                canvas.rotate(f.rotate, (f.x + 16), (f.y + 16));
                f.draw(canvas);
                canvas.restore();
            }

            //draw bumblebees
            for(Bumblebee b: bumblebee)
            {
                canvas.save();
                //Rotate sprite
                canvas.rotate(b.rotate, (b.x + 24), (b.y + 24));
                b.draw(canvas);
                canvas.restore();
            }

            //draw mosquitoes
            for(Mosquito m: mosquito)
            {
                canvas.save();
                //Rotate sprite
                canvas.rotate(m.rotate, (m.x + 12), (m.y + 12));
                m.draw(canvas);
                canvas.restore();
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

            //draw flyswatter
            for(Flyswatter fs: flyswatter)
            {
                fs.draw(canvas);
            }

            //draw showscore
            for(ShowScore ss: showscore)
            {
                ss.draw(canvas);
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