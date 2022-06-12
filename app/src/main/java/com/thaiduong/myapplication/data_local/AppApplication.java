package com.thaiduong.myapplication.data_local;

import android.app.Application;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
