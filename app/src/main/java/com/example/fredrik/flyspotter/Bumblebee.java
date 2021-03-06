package com.example.fredrik.flyspotter;

import android.graphics.Bitmap;
import android.graphics.Canvas;


class Bumblebee extends GameObject {

    private final Animation animation = new Animation();

    private final int randomNum2 = (int) Math.ceil(Math.random() * 48);
    private final int randomNum3 = (int) Math.ceil(Math.random() * 100) + 200;
    int lives = 2;
    private int newspeed = 0;
    private int shake = 1;
    //Rotate is the rotation angle of the bumblebees!
    int rotate = 0;



    public Bumblebee(Bitmap res)
    {
        //Spawning in random x axis, from a number from 0 - 336!
        int randomNum = (int) Math.ceil(Math.random() * 336);
        x = randomNum;
        //Spawning outside the screen!
        y = -64;
        height = 48;
        width = 48;


        //Array to sort out the different sprites
        Bitmap[] image = new Bitmap[2];

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
                x += 6;
                rotate -= 5;
                //cap rotation
                if (rotate <= -45){rotate = -45;}
            }else{
                //rotate back
                if (rotate < 0){rotate += 5;}
            }


            if (x >= 161 + randomNum2) {
                x -= 6;
                rotate += 5;
                //cap rotation
                if (rotate >= 45){rotate = 45;}
            }else{
                //rotate back
                if (rotate > 0){rotate -= 5;}
            }


        }
        //The bumblebee becomes faster if it has one life left, and shakes!
        if (lives < 2){

            newspeed = 3;
            if (shake >= 0 && shake < 3){
                x += 2;
                shake++;
            }
            if (shake >= 3 && shake < 6){
                x -= 2;
                shake++;
                if (shake == 5){shake = 0;}
            }
        }
        //speed
        int speed = 1;
        y += speed + newspeed;

        animation.update();

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }


}
