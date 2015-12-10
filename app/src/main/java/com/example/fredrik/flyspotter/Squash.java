package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Squash extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    int speed = 0;
    //stop is for making the fly squash stop midair for a moment!
    int stop = 0;

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
            stop++;

            animation.update();

            y += speed;

        if (stop > 7) {
            speed += 2;
        }

    }



    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }



}
