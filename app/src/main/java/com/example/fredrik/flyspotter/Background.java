package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Fredrik on 23.11.2015.
 */
public class Background
{
    private Bitmap image;
    private int x, y;

    //Constructor
    public Background(Bitmap res)
    {
        image = res;
    }

    public void update()
    {

    }

    //Background position
    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(image, x, y, null);
    }

}
