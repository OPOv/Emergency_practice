package com.example.lg.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class ShelterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
    }

    public void test_btn(View view)
    {
     Intent intent = new Intent(ShelterActivity.this,test.class);

     startActivity(intent);


    }

}
