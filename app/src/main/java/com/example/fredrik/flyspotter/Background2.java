package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background2
{
    private Bitmap image;
    private int x, y;

    //Constructor
    public Background2(Bitmap res)
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
