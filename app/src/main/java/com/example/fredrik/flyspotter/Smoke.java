package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Smoke extends GameObject
{
    private final Animation animation = new Animation();

    public Smoke(Bitmap res, int x, int y)
    {   //get variables
        super.x = x;
        super.y = y;
        height = 64;
        width = 64;

        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[8];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(40);
    }
    //Update
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
