package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.lang.Math;

/**
 * Created by Evan on 12/17/2015.
 */
public class EnemyBat extends Enemy{

    public EnemyBat(Context context, int screenX, int screenY, int lane){
        super(context, screenX, screenY);

        setImages(context);
        scaleBitmap(screenY);
        type = "bat";

        revive();

        setLane(lane);
    }

    public void setImages(Context context){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_alive);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_dead);
        currentImage = aliveImage;
        hitBox = new Rect(x, y, currentImage.getWidth(), currentImage.getHeight());
        MAX_Y = screenY + currentImage.getHeight();
        MIN_Y = -currentImage.getHeight();
    }

    @Override
    public void update(){
        //Move the enemy
        y += speed;
        if(alive)
            x = (int)(Math.sin(screenX * lifeSpan) * 200);

        if(y > MAX_Y){

        }

        //Move hitbox
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + currentImage.getWidth();
        hitBox.bottom = y + currentImage.getHeight();

        lifeSpan++;
    }
}
