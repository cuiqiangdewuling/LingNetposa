package com.jibo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.modules.LocationAgent;

/**
 * Created by mhz小志 on 2017/5/10.
 */

public class LocationActivity extends Activity {

    public static final String TAG = LocationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findViewById(R.id.btn_getlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LingManager.getInstance().getLocationAgent().getLocationFromServer("123456", new IRequestCallback() {
                    @Override
                    public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                        if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                            Log.d(TAG, "entity  " + entity.toString());
                        } else {
                            Log.d(TAG, "errorCode  " + errorCode);
                            if (error != null) error.printStackTrace();
                        }
                    }
                });
            }
        });
        findViewById(R.id.btn_getAppLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LingManager.getInstance().getLocationAgent().setTimeout(10000);

                LingManager.getInstance().getLocationAgent().getAppLocation(new LocationAgent.ILocationLisenter() {
                    @Override
                    public void onSuccess(double latitude, double longitude, String province, String city) {
                        LingManager.getInstance().getLingLog().LOGD(latitude + " / " + longitude + " / " + city);
                    }

                    @Override
                    public void onFailure() {
                        LingManager.getInstance().getLingLog().LOGD("onFailure");
                    }
                });
            }
        });
        findViewById(R.id.btn_savelocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LingManager.getInstance().getLocationAgent().saveLocationToServer("123456", "吉林省", "吉林市", 101.123123D, 101.123123D, new IRequestCallback() {
                    @Override
                    public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                        if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                            Log.d(TAG, "entity  " + entity.toString());
                        } else {
                            Log.d(TAG, "errorCode  " + errorCode);
                            if (error != null) error.printStackTrace();
                        }
                    }
                });
            }
        });
        findViewById(R.id.btn_getcitylist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LingManager.getInstance().getLocationAgent().getCityDataFromServer(new IRequestCallback() {
                    @Override
                    public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                        if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                            Log.d(TAG, "entity  " + entity.toString());
                        } else {
                            Log.d(TAG, "errorCode  " + errorCode);
                            if (error != null) error.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
