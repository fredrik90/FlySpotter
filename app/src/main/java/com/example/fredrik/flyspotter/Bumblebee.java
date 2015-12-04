package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Fredrik on 03.12.2015.
 */
public class Bumblebee extends GameObject {

    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //Create the random number, for the x axis.
    int randomNum = (int) Math.ceil(Math.random() * 256);
    int randomNum2 = (int) Math.ceil(Math.random() * 48);
    int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    //Create a random number for speed, between 1 - 2!
    float speed = (float) Math.ceil(Math.random() * 1) + 1;
    int lives = 2;
    int newspeed = 0;



    public Bumblebee(Bitmap res, int w, int h, int numFrames)
    {
        //Spawning in random x axis, from a number from 0 - 256!
        x = randomNum;
        //Spawning outside the screen!
        y = -64;
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

        //Make the flies change direction randomly.
        if (y > randomNum3) {
            //Make the flies fly to the cake!
            if (x <= 160 - randomNum2) {
                x += 3.5;
            }
            if (x >= 160 + randomNum2) {
                x -= 3.5;
            }
        }
        //The bumblebee becomes faster if it has one life left!
        if (lives < 2){newspeed = 1;}
        //speed
        y+=speed + newspeed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
