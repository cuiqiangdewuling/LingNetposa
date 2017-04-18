package com.ling.jibonetposa;

import android.content.Context;

import com.haier.uhome.usdk.api.uSDKManager;
import com.ling.jibonetposa.modules.iot.IOTAgent;

import cn.com.broadlink.sdk.BLLet;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class LingManager {

    public static LingManager INSTANCE;
    public static Context mApplication;
    public IOTAgent mIOTAgent;

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
        mApplication = context;
        initBroadLink();
        initHaier();
        initIOTAgent();
    }

    private void initIOTAgent() {
        mIOTAgent = new IOTAgent();
    }

    private void initHaier() {
        uSDKManager.getSingleInstance().init(mApplication);
    }

    private void initBroadLink() {
        BLLet.init(mApplication);
        BLLet.DebugLog.on();
    }

    public IOTAgent getIOTAgent() {
        return mIOTAgent;
    }

    public static Context getAppContext() {
        return mApplication;
    }
}
