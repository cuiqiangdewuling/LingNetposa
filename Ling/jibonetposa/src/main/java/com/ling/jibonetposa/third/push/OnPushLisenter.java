package com.ling.jibonetposa.third.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mhz小志 on 2017/6/1.
 */

public interface OnPushLisenter {

    void onRegistrationId(String regId);

    void onProcessCustomMessage(Bundle bundle);

    void onNotificationReceived(int notifactionId);

    void onNotificationOpened(Bundle bundle);

    void onRichpushCallback(String regId);

    void onConnectionChange(boolean connected);

    void onUnhandled(Intent intent);

    void onReceive(Context context, Intent intent);
}
