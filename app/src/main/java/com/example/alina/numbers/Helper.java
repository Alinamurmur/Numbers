package com.example.alina.numbers;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    private static final String DB_NAME = "reco";
    private static final int DB_VERSION = 1;

    Helper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECORDS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "TIME TEXT");
        insertTime(db,"Max","00:30");
        insertTime(db,"Bill","01:20");
        insertTime(db,"Alina","10:44");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertTime (SQLiteDatabase db, String name, String time){
        ContentValues timeValues = new ContentValues();
        timeValues.put("NAME",name);
        timeValues.put("TIME",time);
        db.insert("RECORDS",null,timeValues);
    }
}