package com.example.lg.emergency;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import net.daum.mf.map.api.*;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ShelterActivity extends AppCompatActivity implements LocationListener {

    FloatingActionButton fab;
    ImageButton btnBack;
    CardView btnShelter;

    public static final String URL_Server = "http://35.221.115.152:3000/";

    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    public MapPOIItem cMarker;  // 현재위치 찍어주는 마커
    public ArrayList<DataItem> shelterList;
    public ArrayList<MapPOIItem> markerList;
    public Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        final MapView mapView;
        mapView = findViewById(R.id.map_view);
        mapView.setDaumMapApiKey(getResources().getString(R.string.kakao_app_key));
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        shelterList = new ArrayList<>();
        markerList = new ArrayList<>();

        // DB Part starts
        final InformationDB infoDB = new InformationDB("ShelterDB", "(id integer primary key autoincrement, Name text not null, Address text not null, " +
                "Latitude text not null, Longitude text not null, PhoneNum text not null);", "(id, Name,Address,Latitude,Longitude,PhoneNum)",
                new URLClass(URL_Server + "ShelterData"));

        File dbFile = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + getPackageName() + "/databases/ShelterDB");
        dao = new Dao(getApplicationContext(), infoDB);

        Log.d("TEST 1 : ", Environment.getDataDirectory().getAbsolutePath() + "/data/" + getPackageName() + "/databases/ShelterDB에 db 있음");

        // DB part end


        // 디버깅 HashKey(해시키) 가져오기
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.lg.emergency", PackageManager.GET_SIGNATURES);
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
        final net.daum.mf.map.api.MapPoint mapPoint = net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(latitude, longitude);
        mapView.setMapCenterPoint(mapPoint, true);

        cMarker = new MapPOIItem();

        btnBack = findViewById(R.id.btn_back);
        fab = findViewById(R.id.fab_c_location);
        btnShelter = findViewById(R.id.btn_hospital);


        /// CardView Fragment 부분
        final ViewPager viewPager = findViewById(R.id.viewPager);

        float density = getResources().getDisplayMetrics().density;
        int partialWidth = (int) (16 * density); // 16dp
        int pageMargin = (int) (8 * density); // 8dp

        int viewPagerPadding = pageMargin - partialWidth;

        // 뷰페이저 패딩!!! 존나 중요함
        viewPager.setPageMargin(-180);
        viewPager.setPadding(0, -85, 10, -85);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mapView.setMapCenterPointAndZoomLevel(
                        net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(shelterList.get(position).getLatitude(), shelterList.get(position).getLongitude()),
                        2, true);
                mapView.selectPOIItem(markerList.get(position), true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /// cardView Fragment 부분 끝

        // eventListener 구현부
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final MapView.POIItemEventListener poiItemEventListener = new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                if(mapPOIItem.getTag() !=0) {
                    viewPager.setCurrentItem(mapPOIItem.getTag() - 1, true);
                    Log.e("selectedItemLocation : ", mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude+", " +
                            mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude);
                }
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, net.daum.mf.map.api.MapPoint mapPoint) {

            }
        };

        MapView.MapViewEventListener mapViewEventListener = new MapView.MapViewEventListener() {
            @Override
            public void onMapViewInitialized(MapView mapView) {

            }

            @Override
            public void onMapViewCenterPointMoved(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }

            @Override
            public void onMapViewZoomLevelChanged(MapView mapView, int i) {

            }

            @Override
            public void onMapViewSingleTapped(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {
                viewPager.setVisibility(View.GONE);
            }

            @Override
            public void onMapViewDoubleTapped(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }

            @Override
            public void onMapViewLongPressed(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragStarted(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragEnded(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }

            @Override
            public void onMapViewMoveFinished(MapView mapView, net.daum.mf.map.api.MapPoint mapPoint) {

            }
        };

        mapView.setMapViewEventListener(mapViewEventListener);


        // 현재 위치로 지도 중앙 이동
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.removePOIItem(cMarker);
                getLocation();
                mapView.setMapCenterPoint(mapPoint, true);
                mapView.setZoomLevel(2, true);

                cMarker.setMapPoint(net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(latitude, longitude));
                cMarker.setItemName("현재위치");
                cMarker.setTag(0);
                cMarker.setMarkerType(MapPOIItem.MarkerType.YellowPin);
                mapView.addPOIItem(cMarker);
                mapView.selectPOIItem(cMarker, true);
            }
        });

        // 가까운 대피소 버튼 눌렀을 때 액션
        btnShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.removePOIItem(cMarker);

                getLocation();

                mapView.setMapCenterPoint(net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
                cMarker.setMapPoint(net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(latitude, longitude));
                cMarker.setItemName("현재위치");
                cMarker.setTag(0);
                cMarker.setMarkerType(MapPOIItem.MarkerType.YellowPin);
                mapView.addPOIItem(cMarker);
                mapView.selectPOIItem(cMarker, true);

                callShelterList(dao, infoDB, mapView, poiItemEventListener, 0.005, 0.2);

                if(shelterList.size() != 0)
                    initFragment(viewPager);
                else
                    Toast.makeText(ShelterActivity.this, "근처에 대피소가 없습니다", Toast.LENGTH_SHORT).show();

                viewPager.setVisibility(View.VISIBLE);
            }
        });


    }



    // 전역변수 위도 경도에 값을 넣어주는 함수
    protected void getLocation() {

        Log.e("lat, lon","lat: " + latitude + " longitude: " + longitude);


//         권한 없을시 권한 다시 부여
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 4);
            return;
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

        Location location = locationManager.getLastKnownLocation(bestProvider);

        if(location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //This is what you need:
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
        }


    }

    // 범위 내의 대피소 목록을 메모리에 불러오는 함수
    public void callShelterList(Dao dao, InformationDB infoDB, MapView mapView, MapView.POIItemEventListener poi, final double latArea, double lonArea){
        shelterList.clear();
        Cursor cursor = dao.getDB("SELECT Name, Longitude, Latitude, PhoneNum FROM " + infoDB.getName());
        cursor.moveToFirst();

        for (int i = 1; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            // 여기서 들어간 숫자들이 데이터를 가져오는 위경도 범위
            if (Math.abs(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Latitude"))) - latitude) < latArea &&
                    Math.abs(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Longitude"))) - longitude) < lonArea) {

                shelterList.add(new DataItem(cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("PhoneNum")),
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex("Latitude"))),
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex("Longitude")))));

            }
        }

        // 거리순으로 정렬
        Collections.sort(shelterList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                if(Math.pow(o1.getLatitude() - latitude, 2) + Math.pow(o1.getLongitude() - longitude, 2) >
                        Math.pow(o2.getLatitude() - latitude, 2) + Math.pow(o2.getLongitude() - longitude, 2))
                    return 1;
                else if(Math.pow(o1.getLatitude() - latitude, 2) + Math.pow(o1.getLongitude() - longitude, 2) <
                        Math.pow(o2.getLatitude() - latitude, 2) + Math.pow(o2.getLongitude() - longitude, 2))
                    return -1;
                else
                    return 0;
            }
        });

        // 마커 리스트 초기화
        markerList.clear();
        for(int i = 1; i <= shelterList.size(); i++){

            MapPOIItem lastMarker = new MapPOIItem();
            markerList.add(lastMarker);
            lastMarker.setItemName(shelterList.get(i - 1).getName());
            lastMarker.setTag(i);
            lastMarker.setMapPoint(net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(shelterList.get(i - 1).getLatitude(), shelterList.get(i - 1).getLongitude()));

            // 기본으로 제공하는 BluePin 마커 모양.
            lastMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
            lastMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mapView.addPOIItem(lastMarker);

        }

        // 마커 선택시 이벤트 리스너
        mapView.setPOIItemEventListener(poi);

    }

    // 아이템 갯수만큼 프래그먼트 만들기
    public void initFragment(ViewPager viewPager){

        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this), shelterList.size(), shelterList);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

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


    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}