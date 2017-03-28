package com.ling.jibonetposa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by cuiqiang on 2017/3/27.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        onNetworkStateChange(isNetworkOnline(context));
    }

    public void onNetworkStateChange(boolean networkState) {

    }

    /**
     * 是否有网
     * @param context
     * @return
     */
    static public boolean isNetworkOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
