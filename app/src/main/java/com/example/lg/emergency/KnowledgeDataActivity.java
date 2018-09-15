package com.example.lg.emergency;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;

public class KnowledgeDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledgedata);

        Intent intent = getIntent();


        TextView title = (TextView) findViewById(R.id.title);
        TextView day = (TextView) findViewById(R.id.day);
        TextView subject = (TextView) findViewById(R.id.subject);


        title.setText(intent.getStringExtra("title"));
        day.setText(intent.getStringExtra("day"));
        subject.setText(intent.getStringExtra("subject"));



    }





}
