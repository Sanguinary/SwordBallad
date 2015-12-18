package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Evan on 11/15/2015.
 */
public class EnemyApple extends Enemy {

    public EnemyApple(Context context, int screenX, int screenY, int lane) {
        super(context, screenX, screenY);

        setImages(context);
        scaleBitmap(screenY);

        revive();

        setLane(lane);
    }

    public void setImages(Context context){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.applealive);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.appledead);
        currentImage = aliveImage;
        hitBox = new Rect(x, y, currentImage.getWidth(), currentImage.getHeight());
        MAX_Y = screenY + currentImage.getHeight();
        MIN_Y = -currentImage.getHeight();
    }
}
