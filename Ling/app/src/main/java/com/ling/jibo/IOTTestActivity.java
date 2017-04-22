package com.ling.jibo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.IOTDevConstant;
import com.ling.jibonetposa.entities.BLAccountEntity;
import com.ling.jibonetposa.entities.HaierAccountEntity;
import com.ling.jibonetposa.entities.PhantomDevicesEntity;
import com.ling.jibonetposa.entities.ResultGetBrandEntity;
import com.ling.jibonetposa.entities.ResultGetDevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.iot.broadlink.BLLoginModel;

import butterknife.ButterKnife;
import cn.com.broadlink.sdk.result.account.BLLoginResult;

import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;

/**
 * Created by mhz小志 on 2017/4/13.
 */

public class IOTTestActivity extends Activity implements View.OnClickListener {

    public static final String TAG = IOTTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        ButterKnife.bind(this);
        initClick();
    }

    private void initClick() {
        findViewById(R.id.btn_getdev).setOnClickListener(this);
        findViewById(R.id.btn_checkbrand).setOnClickListener(this);
        findViewById(R.id.btn_ht_getAccessToken).setOnClickListener(this);
        findViewById(R.id.btn_ht_cancelAuthorized).setOnClickListener(this);
        findViewById(R.id.btn_ht_getAuthorized).setOnClickListener(this);
        findViewById(R.id.btn_bl_login).setOnClickListener(this);
        findViewById(R.id.btn_bl_3login).setOnClickListener(this);
        findViewById(R.id.btn_he_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getdev:
                getDev();
                break;
            case R.id.btn_checkbrand:
                checkBrand();
                break;
            case R.id.btn_ht_cancelAuthorized:
                doHTCancelAuthorized();
                break;
            case R.id.btn_ht_getAuthorized:
                doHTGetAuthorized();
                break;
            case R.id.btn_ht_getAccessToken:
                doHTGetAccessToken();
                break;
            case R.id.btn_bl_login:
                doBLLogin();
                break;
            case R.id.btn_bl_3login:
                doBLThirdLogin();
                break;
            case R.id.btn_he_login:
                doHELogin();
                break;
        }
    }

    private void checkBrand() {
        LingManager.getInstance().getIOTAgent().checkBrandStatus("jibo", new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetBrandEntity brandEntity = (ResultGetBrandEntity) entity;
                    if (brandEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "checkBrand: brandEntity" + brandEntity.toString());
                    }
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "checkBrand: error" + error.getMessage());
                }
            }
        });
    }

    private void getDev() {
        LingManager.getInstance().getIOTAgent().getAllDevices("jibo", 0, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetDevicesEntity brandEntity = (ResultGetDevicesEntity) entity;
                    if (brandEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "getDev: brandEntity" + brandEntity.toString());
                    }
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "getDev: error" + error.getMessage());
                }
            }
        });
    }


    private void doHELogin() {
        String name = "18600941987";
        String pass = "yt19870301";
        LingManager.getInstance().getIOTAgent().doHELogin(name, pass, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    HaierAccountEntity accountEntity = (HaierAccountEntity) entity;
                    if (accountEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "doHELogin: accountEntity" + accountEntity.toString());
                    }
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "doHELogin: error" + error.getMessage());
                }
            }
        });
    }

    private void doBLLogin() {
        String name = "15643407227";
        String pass = "Mahuaizhi123";
        LingManager.getInstance().getIOTAgent().doBLLogin(name, pass, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    BLAccountEntity accountEntity = (BLAccountEntity) entity;
                    if (accountEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "doBLLogin: accountEntity" + accountEntity.toString());
                    }
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "doBLLogin: error" + error.getMessage());
                }
            }
        });
    }

    private void doBLThirdLogin() {
        String name = "20";
        LingManager.getInstance().getIOTAgent().doBLThirdAuth(name, new BLLoginModel.BLTaskListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(Object result) {
                BLLoginResult loginResult = (BLLoginResult) result;
                LingManager.getInstance().getLingLog().LOGD("doBLThirdLogin " + loginResult.getMsg());
            }
        });
    }

    public void doHTGetAuthorized() {
        String code = "0f0003e906fa8a0fd49c62b8c819aad9c599ea580652eb305e8a28fabfbc6b20";
        LingManager.getInstance().getIOTAgent().getPhantomAuthorized(code, new IRequestCallback() {
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
        LingManager.getInstance().getIOTAgent().cancelAuthorized("123456", 1, new IRequestCallback() {
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
        LingManager.getInstance().getIOTAgent().getTokenFromServer("123456", IOTDevConstant.BRAND_TYPE_PHANTOM, new IRequestCallback() {
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
