package com.ling.jibo;

import android.app.Application;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.utils.LingLog;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LingManager.getInstance().init(this);
        LingLog.on();
    }
}
