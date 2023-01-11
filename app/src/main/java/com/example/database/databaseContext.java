package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class databaseContext extends SQLiteOpenHelper {

    public databaseContext(@Nullable Context context) {
        super(context, "todoList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table tasks( id integer primary key autoincrement, description varchar)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "insert into tasks(description) values('" + task +"')";
        db.execSQL(query);
    }
    public ArrayList<String> getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select description from tasks";
        Cursor cursor = db.rawQuery(query);
        
        return new ArrayList<String>();
    }
}
