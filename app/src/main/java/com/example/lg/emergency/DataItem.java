package com.example.lg.emergency;

/**
 * Created by davki on 2018-09-29.
 */

public class DataItem {
    private String name, phoneNum;
    private double latitude, longitude;

    public String getName() { return name; }
    public String getPhoneNum() { return phoneNum; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public DataItem(String name, String phoneNum, double latitude, double longitude){
        this.name = name;
        this.phoneNum = phoneNum;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
