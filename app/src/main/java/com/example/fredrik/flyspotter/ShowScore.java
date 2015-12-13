package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class ShowScore extends GameObject {

    private final Animation animation = new Animation();
    private float speed = 0;

    public ShowScore(Bitmap res, int x, int y)
    {
        super.x = x;
        super.y = y;
        height = 48;
        width = 48;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[20];

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
