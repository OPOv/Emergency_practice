package com.example.lg.emergency;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private InformationDB infoDB;

    public Dao(Context context, InformationDB infoDB){
        this.context = context;
        this.infoDB = infoDB;

        database = context.openOrCreateDatabase(infoDB.getName(), context.MODE_PRIVATE, null);
    }

    public void JsonPasing(String jsonData, String[] attribute) {
        try {
            JSONArray JSONArr = new JSONArray(jsonData);

            for(int i=0;i<JSONArr.length();i++) {
                JSONObject JSONObj = JSONArr.getJSONObject(i);

                String val0 = JSONObj.getString(attribute[0]);
                String val1 = JSONObj.getString(attribute[1]);
                String val2 = JSONObj.getString(attribute[2]);
                String val3 = JSONObj.getString(attribute[3]);
                String val4 = JSONObj.getString(attribute[4]);

                String sql = "INSERT INTO " + infoDB.getName() + infoDB.getAttribute1()
                        + " VALUES(" + i + ", '" + val0 + "', '" + val1 + "', '" + val2 + "', '" + val3 + "', '" + val4 + "');";

                ExecuteSQL(sql);
            }
        }catch(JSONException e){
            Log.e("TEST1 JSON PARSE", "JSON ERROR! -" + e);
        }
    }
/*
    public void CreateKnowledgeData()
    {
        List<KnowledgeItem> items = new ArrayList<>();

        // 차후 수정예정
        KnowledgeItem[] item = new KnowledgeItem[9];
        item[0] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변"," "," ");
        item[1] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병"," "," ");
        item[2] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변"," "," ");
        item[3] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변"," "," ");
        item[4] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병"," "," ");
        item[5] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변"," "," ");
        item[6] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변"," "," ");
        item[7] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병"," "," ");
        item[8] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변"," "," ");

        for(int i = 0; i < item.length; i++)
            items.add(item[i]);

        for(int i = 0; i<items.size();i++) {

            String sql = "INSERT INTO " + infoDB.getName() + infoDB.getAttribute1()
                    + "VALUES(" + i + ", '" + items.get(i).getTitle() + "', '" + items.get(i).getDate()
                    + "', '" + items.get(i).getSubtitle() + "', '" + items.get(i).getDetail()
                    + "', '" + items.get(i).getImagesrc() + "');";

            ExecuteSQL(sql);
        }

    }
    */

    public void ExecuteSQL(String sql)
    {
        try {
            database.execSQL(sql);
            //Log.d("TEST1 DB INSERT","입력완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor getDB( String sql)
    {
        //String sql = "SELECT * FROM " + infoDB.getName() + " WHERE " + columnName + " LIKE "
        //                                                + "\'" + searchData + "\';";

        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.getCount() == 0)
            Log.d("TEST CURSOR", "찾지못함");

        return cursor;
/*
        if(cursor.getCount() == 0)
            Log.d("TEST CURSOR", "찾지못함");


        while ( cursor.moveToNext())
        {

        }
        cursor.close();

        return new ArrayList<>();
 */
    }

}
