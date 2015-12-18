package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Evan on 12/17/2015.
 */
public class EnemySpikeBall extends Enemy {

    public EnemySpikeBall(Context context, int screenX, int screenY, int lane) {
        super(context, screenX, screenY);

        setImages(context);
        scaleBitmap(screenY);

        type = "spikeBall";

        revive();

        setLane(lane);
    }

    public void setImages(Context context){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_ball);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_ball);
        currentImage = aliveImage;
        hitBox = new Rect(x, y, currentImage.getWidth(), currentImage.getHeight());
        MAX_Y = screenY + currentImage.getHeight();
        MIN_Y = -currentImage.getHeight();
    }

    public void die(){
        alive = true;
    }
}
