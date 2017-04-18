package com.ling.jibo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedCodeEntity;
import com.ling.jibonetposa.entities.PhantomDevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import butterknife.ButterKnife;

/**
 * Created by mhz小志 on 2017/4/13.
 */

public class IOTTestActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "Ling ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        ButterKnife.bind(this);
        initClick();
    }

    private void initClick() {
        findViewById(R.id.btn_ht_getAccessToken).setOnClickListener(this);
        findViewById(R.id.btn_ht_cancelAuthorized).setOnClickListener(this);
        findViewById(R.id.btn_ht_getAuthorized).setOnClickListener(this);
        findViewById(R.id.btn_ht_getDevices).setOnClickListener(this);
        findViewById(R.id.btn_bl_login).setOnClickListener(this);
        findViewById(R.id.btn_bl_getDevices).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ht_cancelAuthorized:
                doHTCancelAuthorized();
                break;
            case R.id.btn_ht_getAuthorized:
                doHTGetAuthorized();
                break;
            case R.id.btn_ht_getAccessToken:
                doHTGetAccessToken();
                break;
            case R.id.btn_ht_getDevices:
                doHTGetDevice();
                break;
            case R.id.btn_bl_login:
                doBLLogin();
                break;
            case R.id.btn_bl_getDevices:
                doBLGetDevices();
                break;
        }
    }

    private void doBLGetDevices() {

    }

    private void doBLLogin() {
//        LingManager.getInstance().getIOTAgent().doBLLogin();
    }

    public void doHTGetAuthorized() {
        String code = "0f0003e906fa8a0fd49c62b8c819aad9c599ea580652eb305e8a28fabfbc6b20";
        AuthorizedCodeEntity authorizedEntity = new AuthorizedCodeEntity();
        authorizedEntity.setUserId("123456");
        authorizedEntity.setAuthorizedCode(code);
        LingManager.getInstance().getIOTAgent().getPhantomAuthorized(authorizedEntity, new IRequestCallback() {
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

    public void doHTCancelAuthorized() {
        AuthorizedCodeEntity authorizedEntity = new AuthorizedCodeEntity();
        authorizedEntity.setUserId("123456");
        LingManager.getInstance().getIOTAgent().cancelPhantomAuthorized(authorizedEntity, new IRequestCallback() {
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

    public void doHTGetAccessToken() {
        LingManager.getInstance().getIOTAgent().getPhantomTokenFromServer("123456", new IRequestCallback() {
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

    public void doHTGetDevice() {
        String access_token = "d715bea1d64bd4c19693cdaeb4b1b3aecbbca5f0f84fadd0e852de7763e4d8b9";
        LingManager.getInstance().getIOTAgent().getPhantomDevicesFromPhantom(access_token, new IRequestCallback() {
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
