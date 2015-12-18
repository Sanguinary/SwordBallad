package com.evanalbitz.balladofthesworddancer;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by txs2038 on 12/9/2015.
 */
public class EnemySpawnPattern {

    private ArrayList<Enemy> mEnemiesToSpawn;
    private ArrayList<Integer> mTimeToSpawnEnemy;
    private int mNumberOfEnemies;
    private int mEnemyIndex;

    private Point deviceSize;

    private DataStore mDataStore;

    public EnemySpawnPattern(String fileName, Context ctx){
        mDataStore = DataStore.get(ctx);
        mEnemiesToSpawn = new ArrayList<>();
        mTimeToSpawnEnemy = new ArrayList<>();
        mEnemyIndex = 0;

        // Get screen width for enemy placement
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        deviceSize = new Point();

        // Get device width/height in various API levels
        if(android.os.Build.VERSION.SDK_INT >= 13){
            display.getSize(deviceSize);
        } else {
            deviceSize.x = display.getWidth();
            deviceSize.y = display.getHeight();
        }

        // Load in the current level file
        JSONArray levelData = mDataStore.getJSONArrayFromStorage(fileName);
        try{
            for(int i = 0; i < levelData.length(); i++){
                JSONObject enemy = levelData.getJSONObject(i);

                switch(enemy.getString("enemyType")){
                    case "apple":
                        mEnemiesToSpawn.add(new EnemyApple( ctx,
                                                            deviceSize.x,
                                                            deviceSize.y,
                                                            Integer.parseInt(enemy.getString("lane"))));
                        break;
                    case "log":
                        break;
                    case "bat":
                        mEnemiesToSpawn.add(new EnemyBat(   ctx,
                                                            deviceSize.x,
                                                            deviceSize.y,
                                                            Integer.parseInt(enemy.getString("lane"))));
                        break;
                    case "slime":
                        mEnemiesToSpawn.add(new EnemySlime( ctx,
                                                            deviceSize.x,
                                                            deviceSize.y,
                                                            Integer.parseInt(enemy.getString("lane")),
                                                            true));
                        break;
                    case "spikeBall":
                        mEnemiesToSpawn.add(new EnemySpikeBall( ctx,
                                                                deviceSize.x,
                                                                deviceSize.y,
                                                                Integer.parseInt(enemy.getString("lane"))));
                    default:
                        break;
                }

                mTimeToSpawnEnemy.add(Integer.parseInt(enemy.getString("spawnTime")));
            }
        } catch(Exception e){
            Log.d("SwordBallad", "EnemySpawnPattern constructor: " + e.getMessage());
        }

        mNumberOfEnemies = mEnemiesToSpawn.size();
    }

    // Returns the next
    public Enemy getNextEnemy(){
        return mEnemiesToSpawn.get(mEnemyIndex++);
    }

    // Returns the number of unspawned enemies
    public int getRemainingEnemies(){
        return mNumberOfEnemies - (mEnemyIndex + 1);
    }

    // Gets the time till next enemy will appear
    public int timeToNextEnemy(){
        if(mEnemyIndex < mEnemiesToSpawn.size()){
            return mTimeToSpawnEnemy.get(mEnemyIndex);
        } else {
            return -1;
        }
    }
}
