package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Evan on 12/17/2015.
 */
public class EnemySlime extends Enemy{

    private boolean firstSlime;

    public EnemySlime(Context context, int screenX, int screenY, int lane, boolean firstSlime){
        super(context, screenX, screenY);

        setImages(context);
        scaleBitmap(screenY);
        type = "slime";

        this.firstSlime = firstSlime;

        revive();

        setLane(lane);
    }

    public void setImages(Context context){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.slime);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.slime_dead);
        currentImage = aliveImage;
        hitBox = new Rect(x, y, currentImage.getWidth(), currentImage.getHeight());
        MAX_Y = screenY + currentImage.getHeight();
        MIN_Y = -currentImage.getHeight();
    }

    public boolean getFirstSlime(){return firstSlime;}
}
