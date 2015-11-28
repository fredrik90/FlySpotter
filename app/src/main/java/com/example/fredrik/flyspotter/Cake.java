package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Fredrik on 24.11.2015.
 */
public class Cake extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    public Cake(Bitmap res, int w, int h, int numFrames)
    {
        x = 128;
        y = 334;
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
        animation.setDelay(85);
    }

    public void update()
    {

        animation.update();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
