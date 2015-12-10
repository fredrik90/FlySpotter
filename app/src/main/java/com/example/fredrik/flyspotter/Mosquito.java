package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Mosquito extends GameObject {

    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //Create the random number, for the x axis.
    int randomNum = (int) Math.ceil(Math.random() * 360);
    int randomNum2 = (int) Math.ceil(Math.random() * 12);
    int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    double speed = 1.6;
    boolean move = true;
    boolean startmove = true;
    boolean resetacceleration = false;
    boolean setacceleration = true;
    double acceleration = 0;
    int movedirection;
    //Rotate is the rotation angle of the mosquitoes!
    int rotate = 0;



    public Mosquito(Bitmap res, int w, int h, int numFrames)
    {
        //Spawning in random x axis, from a number from 0 - 256!
        x = randomNum;
        //Spawning outside the screen!
        y = -48;
        height = h;
        width = w;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }


        animation.setFrames(image);
        animation.setDelay(2);

    }

    public void update()
    {

        if (startmove == true){

            if (x <= 192) {
                x -= 5;
            }

            if (x > 192) {
                x += 5;
            }

        }

        if (move == true){

            if (x <= 16) {
                movedirection = 2;
                startmove = false;
                resetacceleration = true;
                if (setacceleration == true){
                    setacceleration = false;
                    acceleration = 0;
                }
            }

            if (x >= 352) {
                movedirection = 1;
                startmove = false;
                resetacceleration = true;
                if (setacceleration == true){
                    setacceleration = false;
                    acceleration = 0;
                }
            }

        }
        if (movedirection == 1 && move == true){
            x -= acceleration;
            rotate += acceleration;
            //cap rotation
            if (rotate >= 45){rotate = 45;}
        }

        if (movedirection == 2 && move == true){
            x += acceleration;
            rotate -= acceleration;
            //cap rotation
            if (rotate <= -45){rotate = -45;}
        }

        if (resetacceleration == true){
        acceleration += 0.25;}

        //cap acceleration
        if (acceleration >= 5)
        {
            acceleration = 5;
            resetacceleration = false;
            setacceleration = true;
        }

        //Make the flies change direction randomly.
        if (y > randomNum3) {
            //Make the flies fly to the cake!
            if (x <= 159 - randomNum2) {
                x += 5;
                move = false;
                rotate -= 5;
                //cap rotation
                if (rotate <= -45){rotate = -45;}
            }else{
                //rotate back
                if (rotate < 0){rotate += 5;}
            }


            if (x >= 161 + randomNum2) {
                x -= 5;
                move = false;
                rotate += 5;
                //cap rotation
                if (rotate >= 45){rotate = 45;}
            }else{
                //rotate back
                if (rotate > 0){rotate -= 5;}
            }


        }
        //speed
        y += speed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

}
