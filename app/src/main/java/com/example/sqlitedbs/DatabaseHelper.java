package com.example.sqlitedbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CV.sqd";
    public static final String TABLE_NAME = "User_data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PHONE";
    public static final String COL_5 = "DESCRIPTION";
    public static final String COL_6 = "DEGREE";

    public DatabaseHelper(MainActivity context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqd) {
        sqd.execSQL(" CREATE TABLE " +TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"NAME TEXT, EMAIL TEXT, PHONE INTEGER, DESCRIPTION TEXT,DEGREE TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqd, int oldVersion, int newVersion) {
        sqd.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqd);
    }

    public boolean insertion(String Name,String Email,String Phone_Number,String Description,String Degree )
    {
        SQLiteDatabase sqd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Email);
        contentValues.put(COL_4,Phone_Number);
        contentValues.put(COL_5,Description);
        contentValues.put(COL_6,Degree);
        long store = sqd.insert(TABLE_NAME,null,contentValues);
        if(store == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor showData()
    {
        SQLiteDatabase sqd = this.getWritableDatabase();
        Cursor res = sqd.rawQuery(" select * from "+ TABLE_NAME, null);
        return res;
    }
}
