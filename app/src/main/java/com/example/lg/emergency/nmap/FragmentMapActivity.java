package com.example.lg.emergency.nmap;

/**
 * Created by davki on 2018-09-17.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.lg.emergency.R;
import com.nhn.android.maps.NMapView;

public class FragmentMapActivity extends FragmentActivity {

    private NMapView mMapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.framents);

        mMapView = findViewById(R.id.mapView);

        // initialize map view
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
    }
}
