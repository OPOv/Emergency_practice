package com.example.lg.emergency;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lg.emergency.nmap.NMapViewer;

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

        List<KnowledgeItem> items = new ArrayList<>();
        // 나중에 갯수 받아와서 크기 조절하는 걸로 수정 필요
        KnowledgeItem[] item = new KnowledgeItem[9];
        item[0] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변");
        item[1] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병");
        item[2] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변");
        item[3] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변");
        item[4] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병");
        item[5] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변");
        item[6] = new KnowledgeItem("심폐소생술 실습", "2017/10/5", "천재지변");
        item[7] = new KnowledgeItem("ADV 사용 방법", "2018/05/17", "질병");
        item[8] = new KnowledgeItem("지진발생시 대피요령", "2016/05/16", "천재지변");

        for(int i = 0; i < item.length; i++)
            items.add(item[i]);

        mainRecycler.setAdapter(new KnowledgeAdapter(mContext, items, R.layout.activity_main));
    }

    public void shelter_btn(View view) { //go to shelter class

        Intent Myintent = new Intent(getApplicationContext(), ShelterActivity.class);
        startActivity(Myintent);

    }

    public void hospital_btn(View view) { //go to hospital class

        Intent Myintent = new Intent(getApplicationContext(), NMapViewer.class);
        startActivity(Myintent);

    }


}
