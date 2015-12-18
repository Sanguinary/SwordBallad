package com.evanalbitz.balladofthesworddancer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Evan on 11/16/2015.
 */
public class GameView extends SurfaceView implements Runnable {

    enum GameState{
        START,
        GAME,
        GAME_OVER
    }
    Context context;
    volatile boolean playing;
    Thread gameThread = null;
    private Player player;
    private ArrayList<Enemy> spawnedEnemies;
    private ArrayList<Heart> hearts;

    private int screenX;
    private int screenY;

    private int timer = 0;
    private int nextEnemySpawn = 0;
    int totalEnemies = 0;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private EnemySpawnPattern mSpawnPattern;
    private DataStore mDataStore;

    boolean waveDone = false;

    private Bitmap backgroundImage;

    final int MAX_HEALTH = 3;
    int playerHealth = 0;

    private GameState curGameState;

    public GameView(Context context, int x, int y, int levelNumber){
        super(context);
        this.context = context;

        backgroundImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.woodsbackground);

        screenX = x;
        screenY = y;

        spawnedEnemies = new ArrayList<>();
        //Initialize drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        playerHealth = MAX_HEALTH;

        //fill hearts ArrayList
        hearts = new ArrayList<>();
        for(int i = 0; i < MAX_HEALTH; i++){
            Heart heart = new Heart(context, screenX, screenY, i);
            hearts.add(heart);
        }

        mDataStore = DataStore.get(context);

        // Pick a random level
        // TODO: Make a level select screen
        //--------------
        Random rand = new Random();
        int r = (rand.nextInt() % 5) + 1;
        //--------------

        mSpawnPattern = new EnemySpawnPattern("level_"+levelNumber+"_data", context);

        totalEnemies = mSpawnPattern.getTotalEnemies();

        curGameState = GameState.START;
        startGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){

        //move player and check for enemies in play state
        switch(curGameState){
            case GAME:
                switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        //switch back to idle when you lift up
                        player.idle();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        //make the player switch images
                        if(motionEvent.getX() > screenX / 2){
                            player.attackRight();
                        }
                        else{
                            player.attackLeft();
                        }

                        //Check enemy kills
                        checkHits(motionEvent);

                        break;
                }
                break;

            case START:
                switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        curGameState = GameState.GAME;
                }
                break;

            case GAME_OVER:
                switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        ((Activity)this.context).finish();
                }
                break;
        }

        return true;
    }

    private void startGame(){
        //Initialize game objects
        player = new Player(context, screenX, screenY);

        nextEnemySpawn = mSpawnPattern.timeToNextEnemy();
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
        for(int i = 0; i < spawnedEnemies.size(); i++){
            if(spawnedEnemies.get(i).hitBox.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
                spawnedEnemies.get(i).die();

                //if the killed enemy is a slime, spawn two smaller slimes
                if(spawnedEnemies.get(i).getType() == "slime" && ((EnemySlime)spawnedEnemies.get(i)).getFirstSlime()){
                    EnemySlime slime1 = new EnemySlime(context, screenX, screenY, 0, false);
                    slime1.x = spawnedEnemies.get(i).x + screenX / 10;
                    slime1.y = spawnedEnemies.get(i).y;
                    slime1.currentImage = Bitmap.createScaledBitmap(slime1.currentImage, (int)(slime1.currentImage.getWidth() / 1.5f), (int)(slime1.currentImage.getHeight() / 1.5f), false);

                    EnemySlime slime2 = new EnemySlime(context, screenX, screenY, 0, false);
                    slime2.x = spawnedEnemies.get(i).x - screenX / 10;
                    slime2.y = spawnedEnemies.get(i).y;
                    slime2.currentImage = Bitmap.createScaledBitmap(slime2.currentImage, (int)(slime2.currentImage.getWidth() / 1.5f), (int)(slime2.currentImage.getHeight() / 1.5f), false);

                    spawnedEnemies.add(slime1);
                    spawnedEnemies.add(slime2);
                }
                else if(spawnedEnemies.get(i).getType() == "spikeBall"){
                    damagePlayer();
                }

            }
        }
    }

    private void update(){
        switch(curGameState){
            case START:
                updateStart();
                break;
            case GAME:
                updateGame();
                break;
            case GAME_OVER:
                updateGameOver();
                break;
            default: break;
        }
    }

    private void updateStart(){
        /*paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.argb(255, 0, 0, 0));
        paint.setTextSize(25);
        canvas.drawText("- Tap to begin -", screenX / 2, screenY / 2 - 20, paint);*/
    }


    private void updateGame(){
        // Check for new enemy spawn
        if(nextEnemySpawn != -1 && timer >= nextEnemySpawn){
            spawnedEnemies.add(mSpawnPattern.getNextEnemy());
            nextEnemySpawn = mSpawnPattern.timeToNextEnemy();
        } else if( nextEnemySpawn == -1){
            waveDone = true;
        }

        //move the apples
        for(int i = 0; i < spawnedEnemies.size(); i++){
            spawnedEnemies.get(i).update();
        }

        timer++;

        //countdown the player timer and reset character
        player.countDown();
        player.idle();

        // Filter out offscreen enemies
        for(Iterator<Enemy> i = spawnedEnemies.iterator(); i.hasNext();){

            Enemy e = i.next();


            if(e.isOffScreen()){
                i.remove();
                totalEnemies--;

                if(e.getAlive() && e.getType() != "spikeBall"){
                    damagePlayer();
                }
            }
        }

        //remove all empty elements
        spawnedEnemies.trimToSize();

        if(totalEnemies == 0 && waveDone)
            curGameState = GameState.GAME_OVER;
    }

    private void damagePlayer(){
        playerHealth--;
        player.damaged();
        if(playerHealth >= 0)
            hearts.get(playerHealth).swapImage();
        if(playerHealth <= 0)
            curGameState = GameState.GAME_OVER;
    }


    private void updateGameOver(){
        /*paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.argb(255, 0, 0, 0));
        paint.setTextSize(25);
        canvas.drawText("- Tap to return -", screenX / 2, screenY / 2 - 20, paint);*/
    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            //Lock area of memory for drawing
            canvas = surfaceHolder.lockCanvas();

            //Replace last frame
            //canvas.drawBitmap(backgroundImage, 0, 0, paint);
            canvas.drawColor(Color.argb(255,255,255,255));

            switch(curGameState){
                case START:
                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setColor(Color.argb(255, 0, 0, 0));
                    paint.setTextSize(25);
                    canvas.drawText("- Tap to begin -", screenX / 2, screenY / 2 - 20, paint);
                    break;
                case GAME:
                    //Draw player
                    canvas.drawBitmap(player.getCurrentImage(), player.getX(), player.getY(), paint);

                    //Draw hitboxes
                    /*paint.setColor(Color.argb(255, 255, 255, 255));
                    for(int i = 0; i < NUM_APPLES; i++){
                        canvas.drawRect(apples[i].getHitBox().left, apples[i].getHitBox().top, apples[i].getHitBox().right, apples[i].getHitBox().bottom, paint);
                    }*/

                    //Draw enemies
                    for(int i = 0; i < spawnedEnemies.size(); i++){
                        Enemy tmp = spawnedEnemies.get(i);
                        canvas.drawBitmap(tmp.getCurrentImage(), tmp.getX(), tmp.getY(), paint);
                    }

                    for(int i = 0; i < MAX_HEALTH; i++){
                        Heart tmp = hearts.get(i);
                        canvas.drawBitmap(tmp.getCurrentImage(), tmp.getX(), tmp.getY(), paint);
                    }
                    break;
                case GAME_OVER:
                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setColor(Color.argb(255, 0, 0, 0));
                    paint.setTextSize(25);
                    canvas.drawText("- Tap to return -", screenX / 2, screenY / 2 - 20, paint);
                    break;
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
