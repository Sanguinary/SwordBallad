package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Evan on 11/16/2015.
 */
public class GameView extends SurfaceView implements Runnable {

    Context context;
    volatile boolean playing;
    Thread gameThread = null;
    private Player player;
    private final int NUM_APPLES = 6;
    private EnemyApple[] apples = new EnemyApple[NUM_APPLES];

    private int screenX;
    private int screenY;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Bitmap backgroundImage;

    public GameView(Context context, int x, int y){
        super(context);
        this.context = context;

        backgroundImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.woodsbackground);

        screenX = x;
        screenY = y;

        //Initialize drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        startGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                //switch back to idle when you lift up
                player.idle();
                break;
            case MotionEvent.ACTION_DOWN:
                //Check enemy kills
                checkHits(motionEvent);

                //make the player switch images
                if(motionEvent.getX() > screenX / 2){
                    player.attackRight();
                }
                else{
                    player.attackLeft();
                }
                break;
        }
        return true;
    }

    private void startGame(){
        //Initialize game objects
        player = new Player(context, screenX, screenY);

        //create apples
        for(int i = 0; i < NUM_APPLES; i++){
            apples[i] = new EnemyApple(context, screenX, screenY);
        }
    }

    @Override
    public void run(){
        while(playing){
            update();
            draw();
            control();
        }
    }

    public void checkHits(MotionEvent motionEvent){
        //kill enemies that are intersected
        for(int i = 0; i < NUM_APPLES; i++){
            if(apples[i].hitBox.contains((int)motionEvent.getX(), (int)motionEvent.getY()))
                apples[i].die();
        }
    }

    private void update(){
        //move the apples
        for(int i = 0; i < NUM_APPLES; i++){
            apples[i].update();
        }

        //countdown the player timer and reset character
        player.countDown();
        player.idle();
    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            //Lock area of memory for drawing
            canvas = surfaceHolder.lockCanvas();

            //Replace last frame
           // canvas.drawBitmap(backgroundImage, 0, 0, paint);
            canvas.drawColor(Color.argb(255,0,0,0));

            //Draw player
            canvas.drawBitmap(player.getCurrentImage(), player.getX(), player.getY(), paint);


            //Draw hitboxes
            /*paint.setColor(Color.argb(255, 255, 255, 255));
            for(int i = 0; i < NUM_APPLES; i++){
                canvas.drawRect(apples[i].getHitBox().left, apples[i].getHitBox().top, apples[i].getHitBox().right, apples[i].getHitBox().bottom, paint);
            }*/

            //Draw enemies
            for(int i = 0; i < NUM_APPLES; i++){
                canvas.drawBitmap(apples[i].getCurrentImage(), apples[i].getX(), apples[i].getY(), paint);
            }

            //Unlock and draw
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try{
            Thread.sleep(17);
        }
        catch(InterruptedException e){

        }
    }

    public void pause(){
        playing = false;
        try{
            gameThread.join();
        }
        catch(InterruptedException e){

        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

}
