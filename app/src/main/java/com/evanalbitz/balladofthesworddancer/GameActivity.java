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

        initLevelData();

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
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 0);
            enemy1.put("lane", 1);

            JSONObject enemy2 = new JSONObject();
            enemy2.put("enemyType", "apple");
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
    }
}
