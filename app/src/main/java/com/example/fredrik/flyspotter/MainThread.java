package com.example.fredrik.flyspotter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//The game loop
public class MainThread extends Thread
{
    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    //Constructor
    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){

        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    //When game starts running!
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        int FPS = 30;
        long targetTime = 1000/ FPS;

        while(running)
        {
            startTime = System.nanoTime();
            canvas = null;

            //try looking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                    this.gamePanel.drawText(canvas);
                }
            }

            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try {
                this.sleep(waitTime);
            }catch (Exception e)
            {
                totalTime += System.nanoTime()-startTime;
                frameCount++;
                if(frameCount == FPS)
                {
                    double averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                    frameCount = 0;
                    totalTime = 0;
                    System.out.println(averageFPS);
                }
            }


        }
    }

    public void setRunning(boolean b)
    {
        running = b;
    }

}



