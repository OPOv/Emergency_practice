package com.example.lg.emergency.nmap;

/**
 * Created by davki on 2018-09-17.
 */


import android.app.Application;

/**
 * @author SeJin Lee
 */
public class NMapViewerApplication extends Application {

    private static NMapViewerApplication instance;

    public static NMapViewerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
    }
}