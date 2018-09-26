package com.example.lg.emergency;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DMapView extends AppCompatActivity implements LocationListener {

    FloatingActionButton fab, fabExit, fabHospital, fabLocation;
    ImageButton btnBack;
    FrameLayout layoutMore;
    View bgMore;


    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    public Context mContext;
    public MapPOIItem cMarker;  // 현재위치 찍어주는 마커

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final MapView mapView;
        mapView = findViewById(R.id.map_view);
        mapView.setDaumMapApiKey(getResources().getString(R.string.kakao_app_key));
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // 디버깅 HashKey(해시키) 가져오기
        try {
            PackageInfo info = getPackageManager().getPackageInfo("your.package.name", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        getLocation();
        final MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        mapView.setMapCenterPoint(mapPoint, true);

        cMarker = new MapPOIItem();

        layoutMore = findViewById(R.id.layout_more);
        fabExit = findViewById(R.id.fab_exit);
        fabHospital = findViewById(R.id.fab_hospital);
        fabLocation = findViewById(R.id.fab_location);
        bgMore = findViewById(R.id.background_more);

        btnBack = findViewById(R.id.btn_back);
        fab = findViewById(R.id.fab_more);
        MapPOIItem marker0 = new MapPOIItem();
        marker0.setItemName("00");
        marker0.setTag(1);
        marker0.setMapPoint(MapPoint.mapPointWithGeoCoord(37.5370192,126.87352970000006));
        // 기본으로 제공하는 BluePin 마커 모양.
        marker0.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker0.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker0);

        MapPOIItem marker1 = new MapPOIItem();
        marker1.setItemName("11");
        marker1.setTag(2);
        marker1.setMapPoint(MapPoint.mapPointWithGeoCoord(36.5370192,127.87352970000006));
        // 기본으로 제공하는 BluePin 마커 모양.
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker1);





        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // more layout 열기
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.VISIBLE);
            }
        });

        // 주변 병원 보여주기
        fabHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutMore.setVisibility(View.GONE);
            }
        });

        // 현재위치로 이동
        fabLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();

                cMarker.setItemName("Current Location");
                cMarker.setTag(0);
                cMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude,longitude));
                cMarker.setMarkerType(MapPOIItem.MarkerType.YellowPin);
                mapView.addPOIItem(cMarker);

                mapView.setMapCenterPoint(mapPoint, true);
                mapView.setZoomLevel(3, true);
                layoutMore.setVisibility(View.GONE);
            }
        });

        // more layout 닫기
        bgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
            }
        });
        fabExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
            }
        });
    }

    // 전역변수 위도 경도에 값을 넣어주는 함수
    protected void getLocation() {

        Log.e("lat, lon","lat: " + latitude + " longitude: " + longitude);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

        // 권한 없을시 권한 다시 부여
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
            return;
        }

        Location location = locationManager.getLastKnownLocation(bestProvider);

        if(location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //This is what you need:
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        locationManager.removeUpdates(this);

        //open the map:
        if(location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
