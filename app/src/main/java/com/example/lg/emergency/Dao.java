package com.example.lg.emergency;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 *   Class Dao
 *
 *   1. public Dao(Context context, String dbName) : 데이터베이스 생성
 *   2. public void InsertDB(String jsonData) : 데이터베이스에 jsonData 넣기
 *   3. public ArrayList<String> getDB(String columnName, String searchData) : 데이터베이스에서 찾기
 */

public class Dao {
    private Context context;
    private SQLiteDatabase database;
    private String dbName;

    public Dao(Context context, String dbName){
        this.context = context;
        this.dbName = dbName;

        database = context.openOrCreateDatabase(dbName, context.MODE_PRIVATE, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS "+ dbName +" (Name text not null,"
                    + "                                                       Address text not null,"
                    + "                                                       Latitude double not null,"
                    + "                                                       Longitude double not null,"
                    + "                                                       PhoneNum text primary key not null);";



            database.execSQL(sql);

            Log.d("TEST1 DB CREATE","데이터베이스 생성 완료");

        } catch (Exception e) {
            Log.e("TEST1 DB CREATE", "CREATE TABLE FAILED! -" + e);
            e.printStackTrace();
        }
    }

    public void InsertDB(String jsonData) {

        String name, addr, num;
        double lati, longi;

        try {
            JSONArray JSONArr = new JSONObject(jsonData).getJSONObject("gwcgcom-medical_facility").getJSONArray("row");

            for(int i=0;i<JSONArr.length();i++) {
                JSONObject JSONObj = JSONArr.getJSONObject(i);

                if ((JSONObj.getString("MC_TYPE").equals("병원") || JSONObj.getString("MC_TYPE").equals("병의원")
                        || JSONObj.getString("MC_TYPE").equals("의원") || JSONObj.getString("MC_TYPE").equals("종합병원"))
                        || !dbName.equals("hospitalDB")) {

                    name = JSONObj.getString("MC_NM");
                    addr = JSONObj.getString("ROAD_ADDRESS");
                    lati = JSONObj.getDouble("LAT");
                    longi = JSONObj.getDouble("LNG");
                    num = JSONObj.getString("PHONE_NO");

                    String sql = "INSERT INTO " + dbName + "(Name,Address,Latitude,Longitude,PhoneNum)"
                            + " VALUES('" + name + "', '" + addr + "', " + lati + ", " + longi + ", '" + num + "');";

                    try {
                        database.execSQL(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("TEST1 DB INSERT","입력완료");
        }catch(JSONException e){
            Log.e("TEST1 JSON PARSE", "JSON ERROR! -" + e);
        }
    }

    public ArrayList<String> getDB(String columnName, String searchData)
    {
        String sql = "SELECT * FROM " + dbName + " WHERE " + columnName + " LIKE "
                                                        + "\'" + searchData + "\';";

        Cursor cursor = database.rawQuery(sql, null);

        if(cursor.getCount() == 0)
            Log.d("TEST CURSOR", "찾지못함");


        while ( cursor.moveToNext())
        {
            /*
                Something Code
             */
        }
        cursor.close();

        return new ArrayList<>();
    }

}
