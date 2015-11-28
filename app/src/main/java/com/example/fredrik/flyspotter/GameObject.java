package com.example.fredrik.flyspotter;

import android.graphics.Rect;

/**
 * Created by Fredrik on 24.11.2015.
 */
public abstract class GameObject
{

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    //Sets and gets the attributes
    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }



}
