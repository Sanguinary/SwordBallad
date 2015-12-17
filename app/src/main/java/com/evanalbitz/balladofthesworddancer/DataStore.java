package com.evanalbitz.balladofthesworddancer;

/**
 * Created by txs2038 on 12/16/2015.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DataStore {

    //-----Static attributes-----
    private static final String PREFS_NAME = "ad4JJH9_DATA_STORE_PREFERENCES";
    private static final String KEY_NUM_TIMES_RUN = "ad4JJH9_NUM_TIMES_RUN";

    private static DataStore sDataStore;

    // Accessor
    public static DataStore get(Context ctx){
        if( sDataStore == null){
            sDataStore = new DataStore(ctx);
        }
        return sDataStore;
    }
    //--------------------------

    // Instance attributes
    private int mNumTimesRun;
    private Context mCtx;

    // Private constructor to setup storage
    private DataStore(Context ctx){
        mCtx = ctx;
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mNumTimesRun = settings.getInt(KEY_NUM_TIMES_RUN, 0); // Get number of times run, 0 if not found

        mNumTimesRun++;
    }

    // Retrieves a single JSON object from storage
    public JSONObject getJSONObjectFromStorage(String fileName){
        String filePath = mCtx.getFilesDir() + File.separator + fileName;

        File file = new File(filePath);
        String line = "";
        StringBuilder sb = new StringBuilder();

        if(file.exists()){
            try {
                FileInputStream fis = mCtx.openFileInput(file.getName());
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                while((line = br.readLine()) != null){
                    sb.append(line);
                }

                br.close();
                isr.close();
                fis.close();
            } catch(Exception e){
                Log.d("SwordBallad", "getJSONObjectFromStorage(): " + e.getMessage());
            }
        } else {
            try {
                FileOutputStream fos = mCtx.openFileOutput(file.getName(), Context.MODE_PRIVATE);
                fos.close();
            } catch( Exception e){
                Log.d("SwordBallad", "getJSONObjectFromStorage(): " + e.getMessage());
            }
        }

        String json = sb.toString();
        JSONObject jsonObj = new JSONObject();

        try{
            jsonObj = new JSONObject(json);
        } catch(Exception e){
            Log.d("SwordBallad", "getJSONObjectFromStorage(): " + e.getMessage());
        }

        return jsonObj;
    }

    // Stores a single JSON object in storage
    public void setJSONObjectInStorage(String fileName, JSONObject obj){
        String filePath = mCtx.getFilesDir() + File.separator + fileName;

        File file = new File(filePath);

        try {
            FileOutputStream fos = mCtx.openFileOutput(file.getName(), Context.MODE_PRIVATE);

            fos.write(obj.toString().getBytes());

            fos.close();
        } catch(Exception e){
            Log.d("SwordBallad", "setJSONObjectInStorage(): " + e.getMessage());
        }
    }

    // Retrieves an array of JSON objects from storage
    public JSONArray getJSONArrayFromStorage(String fileName){
        String filePath = mCtx.getFilesDir() + File.separator + fileName;

        File file = new File(filePath);
        String line = "";
        StringBuilder sb = new StringBuilder();

        if(file.exists()){
            try {
                FileInputStream fis = mCtx.openFileInput(file.getName());
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                while((line = br.readLine()) != null){
                    sb.append(line);
                }

                br.close();
                isr.close();
                fis.close();
            } catch(Exception e){
                Log.d("SwordBallad","getJSONArrayFromStorage(): " + e.getMessage());
            }
        } else {
            try {
                FileOutputStream fos = mCtx.openFileOutput(file.getName(), Context.MODE_PRIVATE);
                fos.close();
            } catch( Exception e){
                Log.d("SwordBallad", "getJSONArrayFromStorage(): " + e.getMessage());
            }
        }

        String json = sb.toString();
        JSONArray jsonArray = new JSONArray();

        try{
            jsonArray = new JSONArray(json);
        } catch(Exception e){
            Log.d("SwordBallad", "getJSONArrayFromStorage(): " + e.getMessage());
        }

        return jsonArray;
    }

    // Stores an array of JSON objects in storage
    public void setJSONArrayInStorage(String fileName, JSONArray array){
        String filePath = mCtx.getFilesDir() + File.separator + fileName;

        File file = new File(filePath);

        try {
            FileOutputStream fos = mCtx.openFileOutput(file.getName(), Context.MODE_PRIVATE);

            fos.write(array.toString().getBytes());

            fos.close();
        } catch(Exception e){
            Log.d("SwordBallad", "setJSONArrayInStorage(): " + e.getMessage());
        }
    }

    // Deletes given file
    public void deleteFileInStorage(String fileName){
        String filePath = mCtx.getFilesDir() + File.separator + fileName;

        File file = new File(filePath);

        file.delete();
    }
}

