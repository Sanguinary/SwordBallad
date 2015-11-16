package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Evan on 11/15/2015.
 */
public class Player {
    private Bitmap currentImage;
    private Bitmap idleImage, attackUpImage, attackRightImage, attackLeftImage;
    private final int IDLE = 0;
    private final int ATTTACK_UP = 1;
    private final int ATTACK_RIGHT = 2;
    private final int ATTACK_LEFT = 3;
    private int state;

    private int x, y, screenX, screenY;


    public Player(Context context, int screenX, int screenY){

        this.screenX = screenX;
        this.screenY = screenY;

        state = IDLE;

        idleImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeridle);
        attackUpImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackup);
        attackRightImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackright);
        attackLeftImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerattackleft);

        currentImage = idleImage;

        x = (screenX / 2) - idleImage.getWidth() / 2;
        y = screenY - idleImage.getHeight() - (screenY / 10);
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
