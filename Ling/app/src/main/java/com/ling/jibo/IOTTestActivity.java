package com.ling.jibo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedCodeEntity;
import com.ling.jibonetposa.entities.PhantomDevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.modules.iot.IOTAgent;

import butterknife.ButterKnife;

/**
 * Created by mhz小志 on 2017/4/13.
 */

public class IOTTestActivity extends Activity {

    public static final String TAG = "Ling ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        ButterKnife.bind(this);
        initClick();
    }

    private void initClick() {
        findViewById(R.id.btn_getPhantomTokenFromeServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhantomTokenFromeServer();
            }
        });
        findViewById(R.id.btn_cancelPhantomAuthorized).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPhantomAuthorized();
            }
        });
        findViewById(R.id.btn_getPhantomAuthorized).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhantomAuthorized();
            }
        });
        findViewById(R.id.btn_getPhantomDeviceList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhantomDeviceList();
            }
        });
    }

    public void getPhantomAuthorized() {
        String code = "0f0003e906fa8a0fd49c62b8c819aad9c599ea580652eb305e8a28fabfbc6b20";
        AuthorizedCodeEntity authorizedEntity = new AuthorizedCodeEntity();
        authorizedEntity.setUserId("123456");
        authorizedEntity.setAuthorizedCode(code);
        IOTAgent.getPhantomAuthorized(authorizedEntity, new IRequestCallback() {
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

    public void cancelPhantomAuthorized() {
        AuthorizedCodeEntity authorizedEntity = new AuthorizedCodeEntity();
        authorizedEntity.setUserId("123456");
        IOTAgent.cancelPhantomAuthorized(authorizedEntity, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        });
    }

    public void getPhantomTokenFromeServer() {
        IOTAgent.getPhantomTokenFromServer("123456", new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        });
    }

    public void getPhantomDeviceList() {
        String access_token = "d715bea1d64bd4c19693cdaeb4b1b3aecbbca5f0f84fadd0e852de7763e4d8b9";
        IOTAgent.getPhantomDevicesFromPhantom(access_token, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    PhantomDevicesEntity devicesEntity = (PhantomDevicesEntity) entity;
                    Log.d(TAG, "entity  " + devicesEntity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode + "    /   " + error.getMessage());
                }
            }
        });
    }
}
