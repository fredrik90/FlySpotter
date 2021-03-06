package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Cake extends GameObject
{
    private final Animation animation = new Animation();

    public Cake(Bitmap res, int numFrames)
    {
        //Set variables
        x = 128;
        y = 332;
        dy = 0;
        height = 87;
        width = 128;

        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[numFrames];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(60);
    }
    //Update
    public void update()
    {

        animation.update();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }



}
