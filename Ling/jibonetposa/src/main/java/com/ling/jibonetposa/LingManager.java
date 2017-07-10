package com.ling.jibonetposa;

import android.content.Context;

import com.ling.jibonetposa.modules.IOTAgent;
import com.ling.jibonetposa.modules.LocationAgent;
import com.ling.jibonetposa.modules.PushAgent;
import com.ling.jibonetposa.utils.LingLog;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class LingManager {

    private boolean isInit;

    public static LingManager INSTANCE;
    public Context mApplicationContext;
    public IOTAgent mIOTAgent;
    public LocationAgent mLocationAgent;
    public PushAgent mPushAgent;
    public LingLog mLingLog;
    public String mJiboUserid = "jibo";
    private boolean useTestUserid;

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
        isInit = true;
        mApplicationContext = context;
        initDebug();
        initPush();
    }

    private void initDebug() {
        mLingLog = new LingLog();
    }

    private void initPush() {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(mApplicationContext);            // 初始化 JPush
    }

    private void initIOTAgent() {
        mIOTAgent = new IOTAgent();
    }

    private void initPushAgent() {
        mPushAgent = new PushAgent(mApplicationContext);
    }

    private void initLocationAgent() {
        mLocationAgent = new LocationAgent(mApplicationContext);
    }

    public IOTAgent getIOTAgent() {
        if (mIOTAgent == null)
            initIOTAgent();
        return mIOTAgent;
    }


    public PushAgent getPushAgent() {
        if (mPushAgent == null)
            initPushAgent();
        return mPushAgent;
    }


    public LocationAgent getLocationAgent() {
        if (mLocationAgent == null)
            initLocationAgent();
        return mLocationAgent;
    }

    public Context getAppContext() {
        return mApplicationContext;
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

    public void useTestUserid(boolean b, String testUserid) {
        useTestUserid = b;
        mJiboUserid = testUserid;
    }

    public boolean isUseTestUserid() {
        return useTestUserid;
    }

    public String getTestUserId() {
        return mJiboUserid;
    }
}
