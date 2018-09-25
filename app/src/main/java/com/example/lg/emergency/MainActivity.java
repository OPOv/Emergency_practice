package com.example.lg.emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    RecyclerView mainRecycler;

    public static final String URL_Server = "http://35.221.115.152:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InformationDB infoDB[] = new InformationDB[3];
        infoDB[0] = new InformationDB("HospitalDB", "(id integer primary key autoincrement, Name text not null, Address text not null, " +
                "Latitude text not null, Longitude test not null, PhoneNum text not null);", "(id, Name,Address,Latitude,Longitude,PhoneNum)",
                new URLClass(URL_Server+ "HospitalData"));
        infoDB[1] = new InformationDB("ShelterDB", "(id integer primary key autoincrement, Name text not null, Address text not null, " +
                "Latitude double not null, Longitude double not null, PhoneNum text not null);", "(id, Name,Address,Latitude,Longitude,PhoneNum)",
                new URLClass(URL_Server + "ShelterData"));

        infoDB[2] = new InformationDB("KnowledgeDB", "(id integer primary key autoincrement, Title text not null, Date text not null, " +
                "SubTitle text not null,  ImageSrc text not null);","(id,Title,Date,SubTitle,ImageSrc)",
                new URLClass(URL_Server+"KnowledgeData"));

        File dbFile = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + getPackageName() + "/databases/HospitalDB");

        Dao dao[] = new Dao[infoDB.length];
        if (dbFile.exists()) {
            Log.d("TEST1 : ", Environment.getDataDirectory().getAbsolutePath() + "/data/" + getPackageName() + "/databases/HospitalDB에 db 있음");
            for (int i = 0; i < dao.length; i++)
                dao[i] = new Dao(getApplicationContext(), infoDB[i]);

            //Test DB status
            Cursor cursor[]= new Cursor[infoDB.length];
            for(int i =0; i < infoDB.length; i++) {
                cursor[i] = dao[i].getDB("SELECT * FROM " + infoDB[i].getName() + " LIMIT 1,10");
                Log.d("TEST 1 : ", " getCount : " + cursor[i].getCount());
            }
            if(cursor[0].getCount() == 0 || cursor[1].getCount() == 0 || cursor[2].getCount() == 0)
                StartHttpConnect(infoDB,dao);

        } else {
            for (int i = 0; i < dao.length; i++) {
                dao[i] = new Dao(getApplicationContext(), infoDB[i]);
                dao[i].ExecuteSQL("CREATE TABLE IF NOT EXISTS " + infoDB[i].getName() + infoDB[i].getAttribute());
            }
            // Starting httpConnection
            StartHttpConnect(infoDB,dao);
        }

/*
        for(int i =0; i < 2; i++) {

            HttpCommunication httpComm = new HttpCommunication(getApplicationContext());
            httpComm.URLConnectionForAPI(dao[i],infoDB[i]);
        }

        // 나중에 갯수 받아와서 크기 조절하는 걸로 수정 필요
        final KnowledgeItem[] item = new KnowledgeItem[9];
        item[0] = new KnowledgeItem(R.drawable.forest, "심폐소생술 실습", "September 21, 2018", "천재지변");
        item[1] = new KnowledgeItem(R.drawable.safari, "ADV 사용 방법", "September 21, 2018", "질병");
        item[2] = new KnowledgeItem(R.drawable.snow, "지진발생 대피요령", "September 21, 2018", "천재지변");
        item[3] = new KnowledgeItem(R.drawable.test, "심폐소생술 실습", "September 21, 2018", "천재지변");
        item[4] = new KnowledgeItem(R.drawable.forest, "ADV 사용 방법", "September 21, 2018", "질병");
        item[5] = new KnowledgeItem(R.drawable.safari, "지진발생시 대피요령", "September 21, 2018", "천재지변");
        item[6] = new KnowledgeItem(R.drawable.snow, "심폐소생술 실습", "September 21, 2018", "천재지변");
        item[7] = new KnowledgeItem(R.drawable.test, "ADV 사용 방법", "September 21, 2018", "질병");
        item[8] = new KnowledgeItem(R.drawable.forest, "지진발생 대피요령", "September 21, 2018", "천재지변");

*/

        mContext = this;

        mainRecycler = findViewById(R.id.recycler_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(layoutManager);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        List<KnowledgeItem> items = new ArrayList<>();


        Cursor cursor = dao[2].getDB("SELECT * FROM KnowledgeDB");

        if(cursor.getCount() != 0) {
            KnowledgeItem[] item = new KnowledgeItem[cursor.getCount()];
            int count = 0;

            cursor.moveToFirst();
            do {
                item[count++] = new KnowledgeItem(cursor.getString(cursor.getColumnIndex("ImageSrc")), cursor.getString(cursor.getColumnIndex("Title")),
                        cursor.getString(cursor.getColumnIndex("Date")), cursor.getString(cursor.getColumnIndex("SubTitle")));
            } while (cursor.moveToNext());


            for (int i = 0; i < item.length; i++)
                items.add(item[i]);

            mainRecycler.setAdapter(new KnowledgeAdapter(mContext, items, new URLClass(URL_Server + "DetailData/img/")));

            ItemClickSupport.addTo(mainRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {

                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

/*
                Intent intent = new Intent(getApplicationContext(), KnowledgeDataActivity.class);

                intent.putExtra("image", Integer.toString(item[position - 1].getImage()));
                intent.putExtra("title", item[position - 1].getTitle());
                intent.putExtra("day", item[position - 1].getDate());
                intent.putExtra("subject", item[position - 1].getSubtitle());


                startActivity(intent);
                */

                }
            });
        }
    }

    public void StartHttpConnect(InformationDB[] infoDB,Dao[] dao){
        String jsonData = null;
        JSONArray jsonArr;

        try {
            HttpConnetion httpConn = new HttpConnetion(infoDB[2],dao[2],MainActivity.this);
            jsonData = httpConn.execute().get();
            jsonArr = new JSONArray(jsonData);

            httpConn.GetJsonAndExecuteSQL(jsonArr,new String[]{"TITLE", "DATE", "SUBTITLE", "IMAGESRC"});
            for (int i = 0; i < infoDB.length - 1; i++) {
                httpConn = new HttpConnetion(infoDB[i],dao[i],MainActivity.this);
                httpConn.execute();
            }

        }catch (InterruptedException | JSONException | ExecutionException e) {
            ExceptionHandling exceptHandling = new ExceptionHandling(e,MainActivity.this,"초기 설정중 문제가 발생했습니다. \n지속적으로 문제 발생 시 어플리케이션 개발자에게 문의하십시오.");
            exceptHandling.StartingExceptionDialog();
            e.printStackTrace();
        }
    }

    public void shelter_btn(View view) { //go to shelter class

        Intent Myintent = new Intent(getApplicationContext(), ShelterActivity.class);
        startActivity(Myintent);

    }

    public void hospital_btn(View view) { //go to hospital class

        Intent Myintent = new Intent(getApplicationContext(), DMapView.class);
        startActivity(Myintent);

    }
}
