package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Evan on 11/15/2015.
 */
public class Enemy{
    public Bitmap aliveImage;
    public Bitmap deadImage;
    public Bitmap currentImage;

    public int MAX_Y, MIN_Y;

    public final int MAX_SPEED = 40;

    public final int MIN_SPEED = 20;
    public int speed;
    public int lane;
    public final int MAXLANES = 4;
    public boolean alive;
    public int x, y, screenX, screenY;
    public Random rand;

    public Rect hitBox;

    public Enemy(Context context, int screenX, int screenY){
        rand = new Random();

        this.screenX = screenX;
        this.screenY = screenY;
    }

    public void update(){
        //Move the enemy
        y += speed;

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

    public void setLane(){
        //set the lane and the X position of the enemy
        lane = rand.nextInt(MAXLANES);

        switch(lane){
            case 0:
                x = 0;
                break;

            case 1:
                x = (screenX / 2) - aliveImage.getWidth();
                break;

            case 2:
                x = (screenX / 2) + aliveImage.getWidth() / 2;
                break;

            case 3:
                x = screenX - aliveImage.getWidth();
                break;

        }
    }

    public void die(){
        alive = false;
        currentImage = deadImage;
    }

    public void revive(){
        alive = true;
        currentImage = aliveImage;
        speed = rand.nextInt(MAX_SPEED - MIN_SPEED) + MIN_SPEED;
    }

    public void scaleBitmap(int y){
        if(y < 1000)
            currentImage = Bitmap.createScaledBitmap(currentImage, currentImage.getWidth() / 3, currentImage.getHeight() / 3, false);
        if(y < 1000)
            currentImage = Bitmap.createScaledBitmap(currentImage, currentImage.getWidth() / 2, currentImage.getHeight() / 2, false);
    }

    //Getters and setters
    public Bitmap getCurrentImage(){return currentImage;}

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y) {this.y = y;}

    public Rect getHitBox(){return hitBox;}

    public boolean getAlive() {return alive;}

}
