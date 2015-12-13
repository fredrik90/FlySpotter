package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ShowScore extends GameObject {

    private Animation animation = new Animation();
    float speed = 0;

    public ShowScore(Bitmap res, int w, int h, int numFrames, int x, int y)
    {
        super.x = x;
        super.y = y;
        height = h;
        width = w;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[numFrames];

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
        speed += 0.25;

        y-=speed;

        //Only go through animation once
        if(!animation.playedOnce())
        {
            animation.update();
        }

    }



    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

}
