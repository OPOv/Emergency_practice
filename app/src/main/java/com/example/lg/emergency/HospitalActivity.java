package com.example.lg.emergency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HospitalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_in_right);
    }
}
