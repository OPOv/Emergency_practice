package com.example.lg.emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    RecyclerView mainRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void shelter_btn(View view) { //go to shelter class

        Intent Myintent = new Intent(getApplicationContext(), ShelterActivity.class);
        startActivity(Myintent);

    }

    public void hospital_btn(View view) { //go to hospital class

        Intent Myintent = new Intent(getApplicationContext(), DMapView.class);
        startActivity(Myintent);

    }


}
