package com.jibo;

import android.app.Application;

import com.ling.jibonetposa.LingManager;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LingManager.getInstance().init(this);
        LingManager.getInstance().debugOn();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
