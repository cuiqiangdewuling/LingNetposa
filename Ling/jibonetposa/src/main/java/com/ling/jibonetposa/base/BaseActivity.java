package com.ling.jibonetposa.base;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ling.jibonetposa.ActivityManager;
import com.ling.jibonetposa.handler.SafeHandler;
import com.ling.jibonetposa.receiver.NetworkStateReceiver;

/**
 * Created by cuiqiang on 2017/3/27.
 */

public class BaseActivity extends AppCompatActivity implements SafeHandler.IHandlerMessage{

    protected Handler mDefaultHandler;
    protected String mUniqueIdentifier = "";

    private NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver() {

        @Override
        public void onNetworkStateChange(final boolean networkState) {

            if (networkState) {

            } else {

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUniqueIdentifier = String.valueOf(hashCode());
        mDefaultHandler = new SafeHandler(this);
        ActivityManager.addActivity(this);
    }

    @Override
    public void handlerMessage(Message msg) {}

    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkStateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    public void registerNetworkReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(networkStateReceiver, intentFilter);
    }

}
