package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Fredrik on 24.11.2015.
 */
public class Squash extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    public Squash(Bitmap res, int w, int h, int numFrames, int x, int y)
    {
        super.x = x;
        super.y = y;
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
        animation.setDelay(40);
    }

    public void update()
    {
        //Only go through animation once
        if(!animation.playedOnce())
        {
            animation.update();
        }

        if(animation.playedOnce())
        {
            x = -100;
        }

    }



    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }



}
