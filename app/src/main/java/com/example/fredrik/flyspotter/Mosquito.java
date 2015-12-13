package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Mosquito extends GameObject {

    private final Animation animation = new Animation();

    private final int randomNum2 = (int) Math.ceil(Math.random() * 12);
    private final int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    private boolean move = true;
    private boolean startmove = true;
    private boolean resetacceleration = false;
    private boolean setacceleration = true;
    private double acceleration = 0;
    private int movedirection;
    //Rotate is the rotation angle of the mosquitoes!
    int rotate = 0;



    public Mosquito(Bitmap res)
    {
        //Spawning in random x axis, from a number from 0 - 256!
        int randomNum = (int) Math.ceil(Math.random() * 360);
        x = randomNum;
        //Spawning outside the screen!
        y = -48;
        height = 24;
        width = 24;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[2];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res, i*width, 0, width, height);
        }


        animation.setFrames(image);
        animation.setDelay(2);

    }

    public void update()
    {
        //Make the mosquito go a direction, depending of where it spawns!
        if (startmove){

            if (x <= 192) {
                x -= 5;
            }

            if (x > 192) {
                x += 5;
            }

        }

        //Make the mosquito bounce from side to side.
        if (move){

            if (x <= 16) {
                movedirection = 2;
                startmove = false;
                resetacceleration = true;
                if (setacceleration){
                    setacceleration = false;
                    acceleration = 0;
                }
            }

            if (x >= 352) {
                movedirection = 1;
                startmove = false;
                resetacceleration = true;
                if (setacceleration){
                    setacceleration = false;
                    acceleration = 0;
                }
            }

        }
        // make the mosquito accelerate when changing direction, and rotate the sprite!
        if (movedirection == 1 && move){
            x -= acceleration;
            rotate += acceleration;
            //cap rotation
            if (rotate >= 45){rotate = 45;}
        }

        if (movedirection == 2 && move){
            x += acceleration;
            rotate -= acceleration;
            //cap rotation
            if (rotate <= -45){rotate = -45;}
        }
        //Accelerate
        if (resetacceleration){
        acceleration += 0.25;}

        //cap acceleration
        if (acceleration >= 5)
        {
            acceleration = 5;
            resetacceleration = false;
            setacceleration = true;
        }

        //Make the mosquitoes change direction randomly.
        if (y > randomNum3) {
            //Make the mosquitoes fly to the cake!
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
        int speed = 3;
        y += speed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

}
