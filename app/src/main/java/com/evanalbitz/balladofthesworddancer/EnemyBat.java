package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.lang.Math;

/**
 * Created by Evan on 12/17/2015.
 */
public class EnemyBat extends Enemy{

    public EnemyBat(Context context, int screenX, int screenY){
        super(context, screenX, screenY);

        setImages(context);
        scaleBitmap(screenY);
        name = "bat";

        revive();

        setLane();
    }

    public void setImages(Context context){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_alive);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_dead);
        currentImage = aliveImage;
        hitBox = new Rect(x, y, currentImage.getWidth(), currentImage.getHeight());
        MAX_Y = screenY + currentImage.getHeight();
        MIN_Y = -currentImage.getHeight();
    }

    public void update(){
        //Move the enemy
        y += speed;
        x += (float)Math.asin(screenX * x);

        if(y > MAX_Y){
            y = MIN_Y;
            revive();
            setLane();
        }

        //Move hitbox
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + currentImage.getWidth();
        hitBox.bottom = y + currentImage.getHeight();
    }
}
