package com.example.fredrik.flyspotter;

        import android.graphics.Bitmap;
        import android.graphics.Canvas;

class Fly extends GameObject
{
    private final Animation animation = new Animation();

    private final int randomNum2 = (int) Math.ceil(Math.random() * 32);
    private final int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    //Rotate is the rotation angle of the flies!
    int rotate = 0;



    public Fly(Bitmap res)
    {
        //Spawning in random x axis, from a number from 0 - 336!
        int randomNum = (int) Math.ceil(Math.random() * 336);
        x = randomNum;
        //Spawning outside the screen!
        y = -32;
        height = 32;
        width = 48;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[3];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res, i*width, 0, width, height);
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
        int speed = 2;
        y += speed;

        animation.update();

    }


    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
