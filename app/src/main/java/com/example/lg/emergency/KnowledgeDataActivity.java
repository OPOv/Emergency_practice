package com.example.lg.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

public class KnowledgeDataActivity extends AppCompatActivity {


    private int img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);

        Intent intent = getIntent();

        ImageView imgage = findViewById(R.id.detail_image);
        TextView title = (TextView) findViewById(R.id.detail_name);
        TextView day = (TextView) findViewById(R.id.detal_day);
        TextView subject = (TextView) findViewById(R.id.detail_subject);



        img = Integer.parseInt(intent.getStringExtra("image"));
        imgage.setImageResource(img);
        title.setText(intent.getStringExtra("title"));
        day.setText(intent.getStringExtra("day"));
        subject.setText(intent.getStringExtra("subject"));



    }





}
