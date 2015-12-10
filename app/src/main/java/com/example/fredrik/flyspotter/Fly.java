package com.example.fredrik.flyspotter;

        import android.graphics.Bitmap;
        import android.graphics.Canvas;

public class Fly extends GameObject
{
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //Create the random number, for the x axis.
    int randomNum = (int) Math.ceil(Math.random() * 336);
    int randomNum2 = (int) Math.ceil(Math.random() * 32);
    int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    int speed = 2;
    //Rotate is the rotation angle of the flies!
    int rotate = 0;



    public Fly(Bitmap res, int w, int h, int numFrames)
    {
        //Spawning in random x axis, from a number from 0 - 336!
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
        if (y > randomNum3) {
            //Make the flies fly to the cake!
            if (x <= 159 - randomNum2) {
                x += 4;
                rotate -= 1;
                //cap rotation
                if (rotate <= -25){rotate = -25;}
            }else{
                //rotate back
                if (rotate < 0){rotate += 5;}
            }

            if (x >= 161 + randomNum2) {
                x -= 4;
                rotate += 1;
                //cap rotation
                if (rotate >= 25){rotate = 25;}
            }else{
                //rotate back
                if (rotate > 0){rotate -= 5;}
            }

        }
        //speed
        y += speed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
