package com.example.lg.emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    RecyclerView mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InformationDB infoDB[] = new InformationDB[3];
        infoDB[0] = new InformationDB("HospitalDB", "(id integer primary key autoincrement, Name text not null, Address text not null, " +
                "Latitude text not null, Longitude test not null, PhoneNum text not null);","(id, Name,Address,Latitude,Longitude,PhoneNum)",
                 "http://192.168.1.46:3000/HospitalData");
        infoDB[1] = new InformationDB("ShelterDB", "(id integer primary key autoincrement, Name text not null, Address text not null, " +
                "Latitude double not null, Longitude double not null, PhoneNum text not null);","(id, Name,Address,Latitude,Longitude,PhoneNum)",
                "http://192.168.1.46:3000/ShelterData");
        infoDB[2] = new InformationDB("KnowledgeDB", "(id integer primary key autoincrement, Title text not null, Date text not null, " +
                "SubTitle text not null, Detail text not null, ImageSrc text not null);","(id,Title,Date,SubTitle,Detail,ImageSrc)",
                "http://192.168.1.46:3000/KnowledgeData");

        Dao dao[] = new Dao[3];
        for(int i =0; i < dao.length; i++) {
            dao[i] = new Dao(getApplicationContext(), infoDB[i]);
            dao[i].ExecuteSQL("CREATE TABLE IF NOT EXISTS " + infoDB[i].getName() + infoDB[i].getAttribute());
        }

        for(int i =0; i < infoDB.length; i++) {
            String jsonData = null;
            try {
                jsonData = new HttpConnetion(infoDB[i].getURL()).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(infoDB[i].getName().equals("HospitalDB")) {
                String[] attribute = {"MC_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                dao[i].JsonPasing(jsonData, attribute);
            }
            else if(infoDB[i].getName().equals("ShelterDB")) {
                String[] attribute = {"TSUNAMI_SHELTER_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                dao[i].JsonPasing(jsonData, attribute);
            }
            else if(infoDB[i].getName().equals("KnowledgeDB")){
                String[] attribute = {"TITLE", "DATE", "SUBTITLE", "DETAIL", "IMAGESRC"};
                dao[i].JsonPasing(jsonData, attribute);
            }
        }

        /*
        for(int i =0; i < 2infoDB.length; i++) {

            HttpCommunication httpComm = new HttpCommunication(getApplicationContext());
            httpComm.URLConnectionForAPI(infoDB[i]);
        }
        */

        mContext = this;

        mainRecycler = findViewById(R.id.recycler_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(layoutManager);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);







        List<KnowledgeItem> items = new ArrayList<>();
        // 나중에 갯수 받아와서 크기 조절하는 걸로 수정 필요
        final KnowledgeItem[] item = new KnowledgeItem[9];
        item[0] = new KnowledgeItem(R.drawable.forest,"심폐소생술 실습", "September 21, 2018", "천재지변");
        item[1] = new KnowledgeItem(R.drawable.safari,"ADV 사용 방법", "September 21, 2018", "질병");
        item[2] = new KnowledgeItem(R.drawable.snow,"지진발생 대피요령", "September 21, 2018", "천재지변");
        item[3] = new KnowledgeItem(R.drawable.test,"심폐소생술 실습", "September 21, 2018", "천재지변");
        item[4] = new KnowledgeItem(R.drawable.forest,"ADV 사용 방법", "September 21, 2018", "질병");
        item[5] = new KnowledgeItem(R.drawable.safari,"지진발생시 대피요령", "September 21, 2018", "천재지변");
        item[6] = new KnowledgeItem(R.drawable.snow,"심폐소생술 실습", "September 21, 2018", "천재지변");
        item[7] = new KnowledgeItem(R.drawable.test,"ADV 사용 방법", "September 21, 2018", "질병");
        item[8] = new KnowledgeItem(R.drawable.forest,"지진발생 대피요령", "September 21, 2018", "천재지변");

        /*
        Cursor cursor = dao[2].getDB("SELECT * FROM KnowledgeDB");
        KnowledgeItem[] item = new KnowledgeItem[cursor.getCount()];

         cursor.moveToFirst();

         int count = 0;

        if(cursor != null) {
            do {
                item[count++] = new KnowledgeItem(cursor.getString(cursor.getColumnIndex("Title")), cursor.getString(cursor.getColumnIndex("Date")),
                                                cursor.getString(cursor.getColumnIndex("SubTitle")), cursor.getString(cursor.getColumnIndex("Detail")),
                                                cursor.getString(cursor.getColumnIndex("ImageSrc")));
            }while(cursor.moveToNext());
        }
        */

        for(int i = 0; i < item.length; i++)
            items.add(item[i]);

        mainRecycler.setAdapter(new KnowledgeAdapter(mContext, items));


        ItemClickSupport.addTo(mainRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {


                Intent intent = new Intent(getApplicationContext(),KnowledgeDataActivity.class);

                intent.putExtra("image",Integer.toString(item[position-1].getImage()));
                intent.putExtra("title", item[position-1].getTitle());
                intent.putExtra("day", item[position-1].getDate());
                intent.putExtra("subject", item[position-1].getSubtitle());


                startActivity(intent);
            }
        });

    }
/*
    public class HttpAsyncTask extends AsyncTask<Void, Void, String>
    {
        InformationDB infoDB;
        Context context;

        public HttpAsyncTask(InformationDB infoDB, Context context) {
            this.infoDB = infoDB;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;

            HttpConnetion httpConn = new HttpConnetion();
            result = httpConn.request(infoDB.getURL());

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Dao dao = new Dao(context, infoDB);
            dao.ExecuteSQL("CREATE TABLE IF NOT EXISTS " + infoDB.getName() + infoDB.getAttribute());

            if(infoDB.getName().equals("HospitalDB")) {
                String[] attribute = {"MC_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                dao.JsonPasing(s, attribute);
            }
            else if(infoDB.getName().equals("ShelterDB")) {
                String[] attribute = {"TSUNAMI_SHELTER_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                dao.JsonPasing(s, attribute);
            }
            else if(infoDB.getName().equals("KnowledgeDB")) {
                String[] attribute = {"TITLE", "DATE", "SUBTITLE", "DETAIL", "IMAGESRC"};
                dao.JsonPasing(s, attribute);
                super.onPostExecute(s);
            }


        }
    }
*/
    public void shelter_btn(View view) { //go to shelter class

        Intent Myintent = new Intent(getApplicationContext(), ShelterActivity.class);
        startActivity(Myintent);

    }

    public void hospital_btn(View view) { //go to hospital class

        Intent Myintent = new Intent(getApplicationContext(), DMapView.class);
        startActivity(Myintent);

    }


}
