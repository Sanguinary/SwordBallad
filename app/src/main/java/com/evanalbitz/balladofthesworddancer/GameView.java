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

    private void update(){
        //move the apples
        for(int i = 0; i < NUM_APPLES; i++){
            apples[i].update();
        }
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
