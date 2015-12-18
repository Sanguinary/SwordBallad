package com.evanalbitz.balladofthesworddancer;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private DataStore mDataStore;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataStore = DataStore.get(this);

        //get screen details
        Display display = getWindowManager().getDefaultDisplay();
        Point deviceSize = new Point();

        // Get device width/height in various API levels
        if(android.os.Build.VERSION.SDK_INT >= 13){
            display.getSize(deviceSize);
        } else {
            deviceSize.x = display.getWidth();
            deviceSize.y = display.getHeight();
        }

        level = 2;

        switch(level){
            case 1:
                initLevelOne();
                break;
            case 2:
                initLevelTwo();
                break;
            case 3:
                initLevelFour();
                break;
            case 4:
                initLevelFive();
                break;
            default:
                initLevelData();
        }

        gameView = new GameView(this, deviceSize.x, deviceSize.y);

        setContentView(gameView);
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }

    private void initLevelData(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);

            JSONObject enemy2 = new JSONObject();
            enemy2.put("enemyType", "spikeBall");
            enemy2.put("spawnTime", 100);
            enemy2.put("lane", 1);

            JSONObject enemy3 = new JSONObject();
            enemy3.put("enemyType", "apple");
            enemy3.put("spawnTime", 100);
            enemy3.put("lane", 2);

            JSONObject enemy4 = new JSONObject();
            enemy4.put("enemyType", "apple");
            enemy4.put("spawnTime", 200);
            enemy4.put("lane", 1);

            JSONObject enemy5 = new JSONObject();
            enemy5.put("enemyType", "apple");
            enemy5.put("spawnTime", 300);
            enemy5.put("lane", 2);

            JSONObject enemy6 = new JSONObject();
            enemy6.put("enemyType", "apple");
            enemy6.put("spawnTime", 300);
            enemy6.put("lane", 1);

            JSONObject enemy7 = new JSONObject();
            enemy7.put("enemyType", "apple");
            enemy7.put("spawnTime", 400);
            enemy7.put("lane", 1);

            JSONObject enemy8 = new JSONObject();
            enemy8.put("enemyType", "apple");
            enemy8.put("spawnTime", 500);
            enemy8.put("lane", 2);

            JSONObject enemy9 = new JSONObject();
            enemy9.put("enemyType", "apple");
            enemy9.put("spawnTime", 500);
            enemy9.put("lane", 1);

            array.put(enemy1);
            array.put(enemy2);
            array.put(enemy3);
            array.put(enemy4);
            array.put(enemy5);
            array.put(enemy6);
            array.put(enemy7);
            array.put(enemy8);
            array.put(enemy9);
        }catch(Exception e){
            Log.d("SwordBallad", "initLevelData(): " + e.getMessage());
        }

        mDataStore.setJSONArrayInStorage("test_level", array);

        initLevelOne();
        initLevelTwo();
        initLevelThree();
        initLevelFour();
        initLevelFive();
    }

    private void initLevelOne(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);

            }catch(Exception e){
                Log.d("SwordBallad", "initLevelOne(): " + e.getMessage());
            }

            mDataStore.setJSONArrayInStorage("level_1_data", array);
        }

    private void initLevelTwo(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "bat");
            enemy1.put("spawnTime", 100);
            enemy1.put("lane", 2);

            JSONObject enemy2 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 300);
            enemy1.put("lane", 0);

            JSONObject enemy3 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 300);
            enemy1.put("lane", 3);

            JSONObject enemy4 = new JSONObject();
            enemy1.put("enemyType", "bat");
            enemy1.put("spawnTime", 350);
            enemy1.put("lane", 1);

            JSONObject enemy5 = new JSONObject();
            enemy1.put("enemyType", "bat");
            enemy1.put("spawnTime", 350);
            enemy1.put("lane", 3);

            JSONObject enemy6 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 500);
            enemy1.put("lane", 0);

            JSONObject enemy7 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 500);
            enemy1.put("lane", 1);

            JSONObject enemy8 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 500);
            enemy1.put("lane", 2);

            JSONObject enemy9 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 500);
            enemy1.put("lane", 3);

            JSONObject enemy10 = new JSONObject();
            enemy1.put("enemyType", "bat");
            enemy1.put("spawnTime", 550);
            enemy1.put("lane", 2);

            JSONObject enemy11 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 700);
            enemy1.put("lane", 1);

            JSONObject enemy12 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 800);
            enemy1.put("lane", 1);

            JSONObject enemy13 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 800);
            enemy1.put("lane", 2);

            JSONObject enemy14 = new JSONObject();
            enemy1.put("enemyType", "bat");
            enemy1.put("spawnTime", 900);
            enemy1.put("lane", 1);

            JSONObject enemy15 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 900);
            enemy1.put("lane", 0);

            JSONObject enemy16 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 900);
            enemy1.put("lane", 3);

            JSONObject enemy17 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 1000);
            enemy1.put("lane", 0);

            JSONObject enemy18 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 1000);
            enemy1.put("lane", 1);

            JSONObject enemy19 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 1000);
            enemy1.put("lane", 2);

            JSONObject enemy20 = new JSONObject();
            enemy1.put("enemyType", "slime");
            enemy1.put("spawnTime", 1000);
            enemy1.put("lane", 3);

            }catch(Exception e){
                Log.d("SwordBallad", "initLevelTwo(): " + e.getMessage());
            }

            mDataStore.setJSONArrayInStorage("level_2_data", array);
        }

    private void initLevelThree(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);
            }catch(Exception e){
                Log.d("SwordBallad", "initLevelThree(): " + e.getMessage());
            }

            mDataStore.setJSONArrayInStorage("level_3_data", array);
        }

    private void initLevelFour(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);

            }catch(Exception e){
                Log.d("SwordBallad", "initLevelFour(): " + e.getMessage());
            }

            mDataStore.setJSONArrayInStorage("level_4_data", array);
        }

    private void initLevelFive(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);

            }catch(Exception e){
                Log.d("SwordBallad", "initLevelFive(): " + e.getMessage());
            }

            mDataStore.setJSONArrayInStorage("level_5_data", array);
        }
}
