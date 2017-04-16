package com.ling.jibonetposa.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by mhz小志 on 2017/4/14.
 */

public class LingLog {

    private static boolean isLog = true;

    public static void on() {
        isLog = true;
    }

    public static void off() {
        isLog = false;
    }

    public static final String TAG_D = "[LING_DEBUG]";

    public static void LOGD(String test) {
        LOGD(null, test);
    }

    public static void LOGD(String TAG, String test) {
        if (!isLog) return;
        if (TAG == null || TextUtils.isEmpty(TAG)) {
            Log.d(TAG_D, test);
        } else {
            Log.d(TAG_D + "_" + TAG, test);
        }
    }
}
