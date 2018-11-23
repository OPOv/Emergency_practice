package com.example.lg.emergency;

/**
 * Created by davki on 2018-09-29.
 */

public class DataItem {
    private String name, phoneNum, address;
    private double latitude, longitude;

    public String getName() { return name; }
    public String getPhoneNum() { return phoneNum; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAddress() { return address; }

    public DataItem(String name, String phoneNum, double latitude, double longitude, String address){
        this.name = name;
        this.phoneNum = phoneNum;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}
