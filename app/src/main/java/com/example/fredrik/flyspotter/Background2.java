package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Background2
{
    //Set variables
    private final Bitmap image;
    private int x, y;

    //Constructor
    public Background2(Bitmap res)
    {
        image = res;
    }


    //Background position
    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(image, x, y, null);
    }

}
