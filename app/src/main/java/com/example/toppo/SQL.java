package com.example.toppo;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQL extends SQLiteOpenHelper{

    public SQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table score(id integer primary key autoincrement, user text, score int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList llenar(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "Select * from score order by score desc";
        Cursor records = database.rawQuery(query, null);
        if(records.moveToFirst()){
            do{
                lista.add(records.getString(1));
            }while (records.moveToNext());
        }
        return lista;
    }
}
