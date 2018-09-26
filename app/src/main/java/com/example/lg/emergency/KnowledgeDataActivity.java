package com.example.lg.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class KnowledgeDataActivity extends AppCompatActivity {

    TextView text[] = new TextView[4];
    ImageView image[] = new ImageView[4];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);


        text[0] = (TextView)findViewById(R.id.content_text1);
        text[1] = (TextView)findViewById(R.id.content_text2);
        text[2] = (TextView)findViewById(R.id.content_text3);
        text[3] = (TextView)findViewById(R.id.content_text4);


        image[0] = (ImageView)findViewById(R.id.content_image1);
        image[1] = (ImageView)findViewById(R.id.content_image2);
        image[2] = (ImageView)findViewById(R.id.content_image3);
        image[3] = (ImageView)findViewById(R.id.content_image4);


        for(int i = 0; i <4 ; i++)
        {
            text[i].setVisibility(View.GONE);
            image[i].setVisibility(View.GONE);
        }

        text[0].setVisibility(View.VISIBLE);
        image[0].setVisibility(View.VISIBLE);
        image[0].setImageResource(R.drawable.forest);
        text[0].setText("Hello\nWorld");


        Intent intent = getIntent();

        ImageView imgage = findViewById(R.id.detail_image);
        TextView title = (TextView) findViewById(R.id.detail_name);
        TextView day = (TextView) findViewById(R.id.detal_day);
        TextView subject = (TextView) findViewById(R.id.detail_subject);


        int position  = intent.getIntExtra("variable", 1);


        Glide.with(this).load(MainActivity.URL_Server+"DetailData/img/1.jpg").into(imgage);
        title.setText(intent.getStringExtra("title"));
        day.setText(intent.getStringExtra("day"));
        subject.setText(intent.getStringExtra("subject"));





    }





}
