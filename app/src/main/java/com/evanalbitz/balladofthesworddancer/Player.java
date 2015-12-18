package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Evan on 11/15/2015.
 */
public class Player {
    private Bitmap currentImage;
    private Bitmap idleImage, attackUpImage, attackRightImage, attackLeftImage, damagedImage;
    private final int IDLE = 0;
    private final int ATTTACK_UP = 1;
    private final int ATTACK_RIGHT = 2;
    private final int ATTACK_LEFT = 3;
    private int state;
    private final int TIME_MAX = 10;
    private int currentTime;

    private int startX, startY, x, y, screenX, screenY;


    public Player(Context context, int screenX, int screenY){

        this.screenX = screenX;
        this.screenY = screenY;

        state = IDLE;
        currentTime = 0;

        idleImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeridle);
        attackUpImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackup);
        attackRightImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackright);
        attackLeftImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackleft);
        damagedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_damaged);

        currentImage = idleImage;

        startX = x = (screenX / 2) - idleImage.getWidth() / 3;
        startY = y = screenY - idleImage.getHeight() - (screenY / 5);
    }

    public void idle(){
        //change image if the timer is up
        if(currentTime <= 0)
        {
            currentImage = idleImage;
            x = startX;
        }
    }

    public void attackRight(){
        currentImage = attackRightImage;
        x = startX - currentImage.getWidth() / 3;
        resetTimer();
    }

    public void attackLeft(){
        currentImage = attackLeftImage;
        x = startX - currentImage.getWidth() / 3;
        resetTimer();
    }

    public void damaged(){
        currentImage = damagedImage;
        x = startX - currentImage.getWidth() / 3;
        resetTimer();
    }

    public void resetTimer(){
        currentTime = TIME_MAX;
    }

    public void countDown(){
        currentTime--;
    }

    //Getters
    public Bitmap getCurrentImage() {return currentImage;}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
