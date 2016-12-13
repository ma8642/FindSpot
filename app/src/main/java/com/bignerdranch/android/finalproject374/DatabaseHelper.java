package com.bignerdranch.android.finalproject374;

/**
 * Created by sysadmin on 11/29/16.
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Wren on 10/23/2016.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    private static DatabaseHelper mInstance = null;
    private static String DB_NAME = "FinalCourseDB.sqlite";
    private static String DB_PATH = "";
    private static int DATABASE_VERSION = 1;
    private SQLiteDatabase myDatabase;
    private final Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        if (Build.VERSION.SDK_INT>=15){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }else{
            DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/database/";
        }
        this.myContext = context;
    }

    public void checkAndCoptDatabase(){
        boolean dbExist = checkDatabase();
        if (dbExist){
            Log.d("TAG", "Databse already exists");
        }else{
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "Copy Database Error");
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e){

        }
        if (checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outfileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0 );{
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase(){
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close(){
        if (myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query){
        return this.getReadableDatabase().rawQuery(query,null);
    }
}