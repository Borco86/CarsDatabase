package com.example.rent.carsdatabase;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by RENT on 2017-03-25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
