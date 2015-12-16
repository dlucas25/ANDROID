package com.example.drh3cker.ihebski_swip;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daniel on 16/12/15.
 */
public class SQLite extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE favoritos (id INTEGER PRIMARY KEY AUTOINCREMENT, categoria STRING, titulo STRING, publicacion DATETYME, descripcion STRING, image STRING)";


    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isReadOnly()) {
            db = getWritableDatabase();
        }
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
