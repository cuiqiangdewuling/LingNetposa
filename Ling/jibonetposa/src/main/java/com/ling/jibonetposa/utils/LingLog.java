package com.ling.jibonetposa.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by mhz小志 on 2017/4/14.
 */

public class LingLog {

    private boolean isLog = true;

    public static final int V = 1;
    public static final int D = 2;
    public static final int I = 3;
    public static final int W = 4;
    public static final int E = 5;

    public void on() {
        isLog = true;
    }

    public void off() {
        isLog = false;
    }

    public final String TAG_D = "[LING_DEBUG]";

    public void LOGD(String test) {
        LOGD(null, test);
    }

    public void LOGD(String TAG, String test) {
        if (!isLog) return;
        if (TAG == null || TextUtils.isEmpty(TAG)) {
            Log.d(TAG_D, test);
        } else {
            Log.d(TAG_D + "_" + TAG, test);
        }
    }

    public void v() {
        v((Object) null);
    }

    public void d() {
        d((Object) null);
    }

    public void i() {
        i((Object) null);
    }

    public void w() {
        w((Object) null);
    }

    public void e() {
        e((Object) null);
    }

    public void v(Object message) {
        v((String) null, message);
    }

    public void d(Object message) {
        d((String) null, message);
    }

    public void i(Object message) {
        i((String) null, message);
    }

    public void w(Object message) {
        w((String) null, message);
    }

    public void e(Object message) {
        e((String) null, message);
    }

    public void v(String tag, Object message) {
        llog(1, tag, message);
    }

    public void d(String tag, Object message) {
        llog(2, tag, message);
    }

    public void i(String tag, Object message) {
        llog(3, tag, message);
    }

    public void w(String tag, Object message) {
        llog(4, tag, message);
    }

    public void e(String tag, Object message) {
        llog(5, tag, message);
    }

    public void llog(int type, String tagStr, Object obj) {
        if (isLog) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            byte index = 5;
            String className = stackTrace[index].getFileName();
            String methodName = stackTrace[index].getMethodName();
            int lineNumber = stackTrace[index].getLineNumber();
            String tag ="LING_"+( tagStr == null ? "" : tagStr);
            methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");
            String msg;
            if (obj == null) {
                msg = "Log with null Object";
            } else {
                msg = obj.toString();
            }

            if (msg != null) {
                stringBuilder.append(msg);
            }

            String logStr = stringBuilder.toString();
            switch (type) {
                case 1:
                    Log.v(tag, logStr);
                    break;
                case 2:
                    Log.d(tag, logStr);
                    break;
                case 3:
                    Log.i(tag, logStr);
                    break;
                case 4:
                    Log.w(tag, logStr);
                    break;
                case 5:
                    Log.e(tag, logStr);
            }
        }
    }
}
