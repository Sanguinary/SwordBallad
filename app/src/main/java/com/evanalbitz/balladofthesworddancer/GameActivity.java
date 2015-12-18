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

        level = 1;

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
        }

        gameView = new GameView(this, deviceSize.x, deviceSize.y, level);

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

    private void initLevelOne(){
        JSONArray array = new JSONArray();
        try{
            JSONObject enemy1 = new JSONObject();
            enemy1.put("enemyType", "apple");
            enemy1.put("spawnTime", 25);
            enemy1.put("lane", 1);

            JSONObject enemy2 = new JSONObject();
            enemy2.put("enemyType", "apple");
            enemy2.put("spawnTime", 40);
            enemy2.put("lane", 3);

            JSONObject enemy3 = new JSONObject();
            enemy3.put("enemyType", "apple");
            enemy3.put("spawnTime", 45);
            enemy3.put("lane", 0);

            JSONObject enemy4 = new JSONObject();
            enemy4.put("enemyType", "apple");
            enemy4.put("spawnTime", 80);
            enemy4.put("lane", 1);

            JSONObject enemy5 = new JSONObject();
            enemy5.put("enemyType", "apple");
            enemy5.put("spawnTime", 95);
            enemy5.put("lane", 2);

            JSONObject enemy6 = new JSONObject();
            enemy6.put("enemyType", "apple");
            enemy6.put("spawnTime", 100);
            enemy6.put("lane", 1);

            JSONObject enemy7 = new JSONObject();
            enemy7.put("enemyType", "apple");
            enemy7.put("spawnTime", 105);
            enemy7.put("lane", 2);

            JSONObject enemy8 = new JSONObject();
            enemy8.put("enemyType", "apple");
            enemy8.put("spawnTime", 135);
            enemy8.put("lane", 3);

            JSONObject enemy9 = new JSONObject();
            enemy9.put("enemyType", "apple");
            enemy9.put("spawnTime", 145);
            enemy9.put("lane", 1);

            JSONObject enemy10 = new JSONObject();
            enemy10.put("enemyType", "apple");
            enemy10.put("spawnTime", 170);
            enemy10.put("lane", 0);

            JSONObject enemy11 = new JSONObject();
            enemy11.put("enemyType", "apple");
            enemy11.put("spawnTime", 180);
            enemy11.put("lane", 1);

            JSONObject enemy12 = new JSONObject();
            enemy12.put("enemyType", "apple");
            enemy12.put("spawnTime", 200);
            enemy12.put("lane", 3);

            JSONObject enemy13 = new JSONObject();
            enemy13.put("enemyType", "apple");
            enemy13.put("spawnTime", 215);
            enemy13.put("lane", 0);

            JSONObject enemy14 = new JSONObject();
            enemy14.put("enemyType", "apple");
            enemy14.put("spawnTime", 220);
            enemy14.put("lane", 1);

            JSONObject enemy15 = new JSONObject();
            enemy15.put("enemyType", "apple");
            enemy15.put("spawnTime", 225);
            enemy15.put("lane", 2);

            JSONObject enemy16 = new JSONObject();
            enemy16.put("enemyType", "apple");
            enemy16.put("spawnTime", 230);
            enemy16.put("lane", 3);

            JSONObject enemy17 = new JSONObject();
            enemy17.put("enemyType", "apple");
            enemy17.put("spawnTime", 250);
            enemy17.put("lane", 0);

            JSONObject enemy18 = new JSONObject();
            enemy18.put("enemyType", "apple");
            enemy18.put("spawnTime", 260);
            enemy18.put("lane", 3);

            JSONObject enemy19 = new JSONObject();
            enemy19.put("enemyType", "apple");
            enemy19.put("spawnTime", 270);
            enemy19.put("lane", 3);

            JSONObject enemy20 = new JSONObject();
            enemy20.put("enemyType", "apple");
            enemy20.put("spawnTime", 285);
            enemy20.put("lane", 1);

            JSONObject enemy21 = new JSONObject();
            enemy21.put("enemyType", "apple");
            enemy21.put("spawnTime", 300);
            enemy21.put("lane", 3);

            JSONObject enemy22 = new JSONObject();
            enemy22.put("enemyType", "apple");
            enemy22.put("spawnTime", 310);
            enemy22.put("lane", 1);

            JSONObject enemy23 = new JSONObject();
            enemy23.put("enemyType", "apple");
            enemy23.put("spawnTime", 320);
            enemy23.put("lane", 0);

            JSONObject enemy24 = new JSONObject();
            enemy24.put("enemyType", "apple");
            enemy24.put("spawnTime", 330);
            enemy24.put("lane", 0);

            JSONObject enemy25 = new JSONObject();
            enemy25.put("enemyType", "apple");
            enemy25.put("spawnTime", 345);
            enemy25.put("lane", 3);

            JSONObject enemy26 = new JSONObject();
            enemy26.put("enemyType", "apple");
            enemy26.put("spawnTime", 360);
            enemy26.put("lane", 1);

            JSONObject enemy27 = new JSONObject();
            enemy27.put("enemyType", "apple");
            enemy27.put("spawnTime", 375);
            enemy27.put("lane", 2);

            JSONObject enemy28 = new JSONObject();
            enemy28.put("enemyType", "apple");
            enemy28.put("spawnTime", 380);
            enemy28.put("lane", 3);

            JSONObject enemy29 = new JSONObject();
            enemy29.put("enemyType", "apple");
            enemy29.put("spawnTime", 390);
            enemy29.put("lane", 1);

            JSONObject enemy30 = new JSONObject();
            enemy30.put("enemyType", "apple");
            enemy30.put("spawnTime", 400);
            enemy30.put("lane", 0);

            array.put(enemy1);
            array.put(enemy2);
            array.put(enemy3);
            array.put(enemy4);
            array.put(enemy5);
            array.put(enemy6);
            array.put(enemy7);
            array.put(enemy8);
            array.put(enemy9);
            array.put(enemy10);
            array.put(enemy11);
            array.put(enemy12);
            array.put(enemy13);
            array.put(enemy14);
            array.put(enemy15);
            array.put(enemy16);
            array.put(enemy17);
            array.put(enemy18);
            array.put(enemy19);
            array.put(enemy20);
            array.put(enemy21);
            array.put(enemy22);
            array.put(enemy23);
            array.put(enemy24);
            array.put(enemy25);
            array.put(enemy26);
            array.put(enemy27);
            array.put(enemy28);
            array.put(enemy29);
            array.put(enemy30);

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
            enemy2.put("enemyType", "apple");
            enemy2.put("spawnTime", 300);
            enemy2.put("lane", 0);

            JSONObject enemy3 = new JSONObject();
            enemy3.put("enemyType", "apple");
            enemy3.put("spawnTime", 300);
            enemy3.put("lane", 3);

            JSONObject enemy4 = new JSONObject();
            enemy4.put("enemyType", "bat");
            enemy4.put("spawnTime", 350);
            enemy4.put("lane", 1);

            JSONObject enemy5 = new JSONObject();
            enemy5.put("enemyType", "bat");
            enemy5.put("spawnTime", 350);
            enemy5.put("lane", 3);

            JSONObject enemy6 = new JSONObject();
            enemy6.put("enemyType", "apple");
            enemy6.put("spawnTime", 500);
            enemy6.put("lane", 0);

            JSONObject enemy7 = new JSONObject();
            enemy7.put("enemyType", "apple");
            enemy7.put("spawnTime", 500);
            enemy7.put("lane", 1);

            JSONObject enemy8 = new JSONObject();
            enemy8.put("enemyType", "apple");
            enemy8.put("spawnTime", 500);
            enemy8.put("lane", 2);

            JSONObject enemy9 = new JSONObject();
            enemy9.put("enemyType", "apple");
            enemy9.put("spawnTime", 500);
            enemy9.put("lane", 3);

            JSONObject enemy10 = new JSONObject();
            enemy10.put("enemyType", "bat");
            enemy10.put("spawnTime", 550);
            enemy10.put("lane", 2);

            JSONObject enemy11 = new JSONObject();
            enemy11.put("enemyType", "slime");
            enemy11.put("spawnTime", 700);
            enemy11.put("lane", 1);

            JSONObject enemy12 = new JSONObject();
            enemy12.put("enemyType", "slime");
            enemy12.put("spawnTime", 800);
            enemy12.put("lane", 1);

            JSONObject enemy13 = new JSONObject();
            enemy13.put("enemyType", "slime");
            enemy13.put("spawnTime", 800);
            enemy13.put("lane", 2);

            JSONObject enemy14 = new JSONObject();
            enemy14.put("enemyType", "bat");
            enemy14.put("spawnTime", 900);
            enemy14.put("lane", 1);

            JSONObject enemy15 = new JSONObject();
            enemy15.put("enemyType", "apple");
            enemy15.put("spawnTime", 900);
            enemy15.put("lane", 0);

            JSONObject enemy16 = new JSONObject();
            enemy16.put("enemyType", "apple");
            enemy16.put("spawnTime", 900);
            enemy16.put("lane", 3);

            JSONObject enemy17 = new JSONObject();
            enemy17.put("enemyType", "slime");
            enemy17.put("spawnTime", 1000);
            enemy17.put("lane", 0);

            JSONObject enemy18 = new JSONObject();
            enemy18.put("enemyType", "slime");
            enemy18.put("spawnTime", 1000);
            enemy18.put("lane", 1);

            JSONObject enemy19 = new JSONObject();
            enemy19.put("enemyType", "slime");
            enemy19.put("spawnTime", 1000);
            enemy19.put("lane", 2);

            JSONObject enemy20 = new JSONObject();
            enemy20.put("enemyType", "slime");
            enemy20.put("spawnTime", 1000);
            enemy20.put("lane", 3);

            array.put(enemy1);
            array.put(enemy2);
            array.put(enemy3);
            array.put(enemy4);
            array.put(enemy5);
            array.put(enemy6);
            array.put(enemy7);
            array.put(enemy8);
            array.put(enemy9);
            array.put(enemy10);
            array.put(enemy11);
            array.put(enemy12);
            array.put(enemy13);
            array.put(enemy14);
            array.put(enemy15);
            array.put(enemy16);
            array.put(enemy17);
            array.put(enemy18);
            array.put(enemy19);
            array.put(enemy20);

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
