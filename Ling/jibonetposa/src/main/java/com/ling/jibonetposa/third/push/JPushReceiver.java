package com.ling.jibonetposa.third.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ling.jibonetposa.LingManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by mhz小志 on 2017/3/2.
 */

/**
 * Created by mhz小志 on 2017/6/1.   自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = JPushReceiver.class.getSimpleName();


    /**
     * @param intent Action - JPushInterface.ACTION_REGISTRATION_ID
     *               SDK 向 JPush Server 注册所得到的注册 ID 。
     *               一般来说，可不处理此广播信息。
     *               要深入地集成极光推送，开发者想要自己保存App用户与JPush 用户关系时，则接受此广播，取得 Registration ID 并保存与App uid 的关系到开发者自己的应用服务器上。
     * @param intent JPushInterface.ACTION_MESSAGE_RECEIVED 收到了自定义消息 Push 。
     *               SDK 对自定义消息，只是传递，不会有任何界面上的展示。
     *               如果开发者想推送自定义消息 Push，则需要在 AndroidManifest.xml 里配置此 Receiver action，并且在自己写的 BroadcastReceiver 里接收处理。
     * @param intent JPushInterface.ACTION_NOTIFICATION_RECEIVED
     *               收到了通知 Push。
     *               如果通知的内容为空，则在通知栏上不会展示通知。
     *               但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
     * @param intent JPushInterface.ACTION_NOTIFICATION_OPENED
     *               用户点击了通知。 一般情况下，用户不需要配置此 receiver action。
     *               如果开发者在 AndroidManifest.xml 里未配置此 receiver action，那么，SDK 会默认打开应用程序的主 Activity，相当于用户点击桌面图标的效果。
     *               如果开发者在 AndroidManifest.xml 里配置了此 receiver action，那么，当用户点击通知时，SDK 不会做动作。开发者应该在自己写的 BroadcastReceiver 类里处理，比如打开某 Activity 。
     * @param intent JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION
     *               用户点击了通知栏中自定义的按钮。(SDK 3.0.0 以上版本支持)
     *               使用普通通知的开发者不需要配置此 receiver action。只有开发者使用了 MultiActionsNotificationBuilder 构建携带按钮的通知栏的通知时，可通过该 action 捕获到用户点击通知栏按钮的操作，并自行处理。
     * @param intent JPushInterface.ACTION_CONNECTION_CHANGE
     *               JPush 服务的连接状态发生变化。（注：不是指 Android 系统的网络连接状态。）
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        OnPushLisenter pushLisenter = LingManager.getInstance().getPushAgent().getPushLisenter();
        Bundle bundle = intent.getExtras();
        LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            //send the Registration Id to your server...
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 接收Registration Id : " + regId);
            if (pushLisenter != null) pushLisenter.onRegistrationId(regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            if (pushLisenter != null) pushLisenter.onProcessCustomMessage(bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            if (pushLisenter != null) pushLisenter.onNotificationReceived(notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 用户点击打开了通知");
            if (pushLisenter != null) pushLisenter.onNotificationOpened(bundle);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            String extraExtra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] 用户收到到RICH PUSH CALLBACK: " + extraExtra);
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            if (pushLisenter != null) pushLisenter.onRichpushCallback(extraExtra);
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver]" + intent.getAction() + " connected state change to " + connected);
            if (pushLisenter != null) pushLisenter.onConnectionChange(connected);
        } else {
            LingManager.getInstance().getLingLog().LOGD(TAG, "[JPushReceiver] Unhandled intent - " + intent.getAction());
            if (pushLisenter != null) pushLisenter.onUnhandled(intent);
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

}
