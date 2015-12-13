package com.example.fredrik.flyspotter;

import android.graphics.Rect;

abstract class GameObject
{

    int x;
    int y;
    protected int dx;
    int dy;
    int width;
    int height;

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
