package com.evanalbitz.balladofthesworddancer;

import java.util.ArrayList;

/**
 * Created by txs2038 on 12/9/2015.
 */
public class EnemySpawnPattern {
    private ArrayList<Enemy> enemiesToSpawn;
    private int timeToSpawnEnemy[];
    private int timer;

    public EnemySpawnPattern(){
        enemiesToSpawn = new ArrayList<>();
        timer = 0;
    }
}
