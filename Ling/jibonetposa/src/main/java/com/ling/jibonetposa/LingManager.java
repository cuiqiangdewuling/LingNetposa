package com.ling.jibonetposa;

import android.content.Context;

import com.haier.uhome.usdk.api.uSDKManager;
import com.ling.jibonetposa.modules.IOTAgent;
import com.ling.jibonetposa.utils.LingLog;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class LingManager {

    private boolean isInit;
    public static LingManager INSTANCE;
    public Context mApplication;
    public IOTAgent mIOTAgent;
    public LingLog mLingLog;

    private LingManager() {
        initDebug();
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
        isInit = true;
        mApplication = context;
    }

    private void initDebug() {
        mLingLog = new LingLog();
    }

    public void debugOn() {
        mLingLog.on();
    }

    public void debugOff() {
        mLingLog.off();
    }

    public LingLog getLingLog() {
        return mLingLog;
    }

    private void initIOTAgent() {
        mIOTAgent = new IOTAgent();
        initHaier();
    }

    private void initHaier() {
        uSDKManager.getSingleInstance().init(mApplication);
    }

    public IOTAgent getIOTAgent() {
        if (mIOTAgent == null)
            initIOTAgent();
        return mIOTAgent;
    }

    public Context getAppContext() {
        return mApplication;
    }

}
