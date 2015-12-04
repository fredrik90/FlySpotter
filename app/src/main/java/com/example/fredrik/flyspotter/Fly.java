package com.example.fredrik.flyspotter;

        import android.graphics.Bitmap;
        import android.graphics.Canvas;


/**
 * Created by Fredrik on 24.11.2015.
 */
public class Fly extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //Create the random number, for the x axis.
    int randomNum = (int) Math.ceil(Math.random() * 352);
    int randomNum2 = (int) Math.ceil(Math.random() * 48);
    double speed = 2;



    public Fly(Bitmap res, int w, int h, int numFrames)
    {
        //Spawning in random x axis, from a number from 0 - 256!
        x = randomNum;
        //Spawning outside the screen!
        y = -32;
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
        animation.setDelay(2);

    }

    public void update()
    {

        //Make the flies change direction randomly.
        if (y > randomNum - 200) {
            //Make the flies fly to the cake!
            if (x <= 160 - randomNum2) {
                x += 2;
            }
            if (x >= 160 + randomNum2) {
                x -= 2;
            }
        }
        //speed
        y+=speed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
