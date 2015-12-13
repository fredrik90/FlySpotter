package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Flyswatter extends GameObject {

    private Bitmap spritesheet;
    private Animation animation = new Animation();



    public Flyswatter(Bitmap res, int w, int h, int numFrames, int x, int y)
    {
        //set variables
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
        animation.setDelay(2);

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
