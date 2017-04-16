package com.ling.jibonetposa;

import android.content.Context;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class LingManager {

    public static LingManager INSTANCE;
    public static Context mHaierApplication;

    private LingManager() {
    }

    public static LingManager getInstance() {
        if (null == INSTANCE) {
            synchronized (LingManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new LingManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mHaierApplication = context;
//        uSDKManager sdkMgr = uSDKManager.getSingleInstance();
//        sdkMgr.init(context);
    }

    public static Context getAppContext() {
        return mHaierApplication;
    }
}
