package com.bignerdranch.android.FindSpot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wren on 10/23/2016.
 */

public class DbObject {
    public static DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DbObject(Context context) {
        dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }

}
