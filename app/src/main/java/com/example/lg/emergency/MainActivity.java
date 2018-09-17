package com.example.lg.emergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lg.emergency.nmap.NMapViewer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shelter_btn(View view) { //go to shelter class

        Intent Myintent = new Intent(getApplicationContext(),ShelterActivity.class);
        startActivity(Myintent);

    }

    public void knowledge_btn(View view) { //go to knowledge class

        Intent Myintent = new Intent(getApplicationContext(),KnowledgeActivity.class);
        startActivity(Myintent);

    }

    public void hospiter_btn(View view) { //go to hospiter class

        Intent Myintent = new Intent(getApplicationContext(),NMapViewer.class);
        startActivity(Myintent);

    }
}
