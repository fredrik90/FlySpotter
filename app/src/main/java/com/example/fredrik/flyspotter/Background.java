package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;


class Background {
    //Set variables
    private final Bitmap image;
    private int x, y, dx;

    //Constructor
    public Background(Bitmap res)
    {
        image = res;
    }
    //Update background, give the illusion that the background is moving!
    public void update()
    {
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
        }
    }
    //Draw
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
    public void setVector(int dx)
    {
        this.dx = dx;
    }
}
