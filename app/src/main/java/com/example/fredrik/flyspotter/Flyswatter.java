package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Flyswatter extends GameObject {

    private Animation animation = new Animation();



    public Flyswatter(Bitmap res)
    {
        //set variables
        super.x = 256;
        super.y = 334;
        height = 512;
        width = 96;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[1];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res, i*width, 0, width, height);
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
