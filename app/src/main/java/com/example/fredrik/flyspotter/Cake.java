package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Cake extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    public Cake(Bitmap res, int w, int h, int numFrames)
    {
        //Set variables
        x = 128;
        y = 332;
        dy = 0;
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
