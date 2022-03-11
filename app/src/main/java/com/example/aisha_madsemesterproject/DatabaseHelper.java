package com.example.aisha_madsemesterproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "register.db";
    public static final String TableName = "registeruser";
    public static final String Col1 = "ID";
    public static final String Col2 = "username";
    public static final String Col3 = "password";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR, password VARCHAR )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(db);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }
    public boolean checkUser(String username,String password){
        String[] columns = {Col1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Col2 + "=?" + " and "+ Col3 + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TableName,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        db.close();
        if(count>0)
            return true;
        else
            return false;

    }
}









