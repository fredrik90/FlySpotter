package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;

class Animation
{
    //Set variables
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }
    //Set the speed of animation
    public void setDelay(long d){delay = d;}
    //Update animation
    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length)
        {
            currentFrame = 0;
            playedOnce = true;
        }
    }
    //return current frame
    public Bitmap getImage()
    {
        return frames[currentFrame];
    }
    //Return if played once
    public boolean playedOnce(){return playedOnce;}

}


