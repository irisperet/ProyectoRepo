package com.example.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper{

    private static final String PRENDAS_TABLE_CREATE = "CREATE TABLE Prendas(_id INTEGER PRIMARY KEY AUTOINCREMENT, ocasion TEXT, temporada TEXT, tipo TEXT)";
    private static final String DB_NAME = "Prendas.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;
    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRENDAS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
