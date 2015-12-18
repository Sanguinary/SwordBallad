package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Evan on 12/17/2015.
 */
public class Heart {
    Bitmap aliveImage;
    Bitmap deadImage;
    Bitmap currentImage;
    private final int SPACING = 10;
    private int x, y;

    public Heart(Context context, int screenX, int screenY, int arrayPosition){

        setImages(context, screenY);

        y = screenY / 20;
        x = (arrayPosition * currentImage.getWidth() + (SPACING * arrayPosition)) + SPACING;
    }


    public void setImages(Context context, int screenY){
        aliveImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.heart);
        deadImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.heart_broken);
        currentImage = aliveImage;

        scaleBitmap(screenY);
    }

    public void scaleBitmap(int screenSize){
        if(screenSize < 700)
            currentImage = Bitmap.createScaledBitmap(currentImage, currentImage.getWidth() / 3, currentImage.getHeight() / 3, false);
        else if(screenSize < 1000)
            currentImage = Bitmap.createScaledBitmap(currentImage, currentImage.getWidth() / 2, currentImage.getHeight() / 2, false);
    }

    public void swapImage(){
        if(currentImage == aliveImage)
            currentImage = deadImage;
        else
            currentImage = aliveImage;
    }


    //GETTERS AND SETTERS
    public Bitmap getAliveImage() {
        return aliveImage;
    }

    public Bitmap getDeadImage() {
        return deadImage;
    }


    public Bitmap getCurrentImage() {
        return currentImage;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

}
