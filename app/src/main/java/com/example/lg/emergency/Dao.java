package com.example.lg.emergency;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

/*
 *   Class Dao
 *
 *   1. public Dao(Context context, String dbName) : 데이터베이스 생성
 *   2. public void ExecuteSQL(String sql) : SQL 실행
 *   3. public Cursor getDB( String sql) : 데이터베이스에서 찾기
 */

public class Dao {
    private Context context;
    private SQLiteDatabase database;
    private InformationDB infoDB;

    public Dao(Context context, InformationDB infoDB){
        this.context = context;
        this.infoDB = infoDB;

        database = context.openOrCreateDatabase(infoDB.getName(), context.MODE_PRIVATE, null);
    }

    public void ExecuteSQL(String sql) {
        database.execSQL(sql);
    }

    public Cursor getDB( String sql)
    {
        Cursor cursor = database.rawQuery(sql, null);

        return cursor;
    }

}
