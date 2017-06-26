package com.jibo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.IOTDevConstant;
import com.ling.jibonetposa.entities.iot.BrandStatusEntity;
import com.ling.jibonetposa.entities.iot.DevicesEntity;
import com.ling.jibonetposa.entities.iot.HaierAccountEntity;
import com.ling.jibonetposa.entities.iot.PutUpdateDevNameEntity;
import com.ling.jibonetposa.entities.iot.ResultSaveAuthDataEntity;
import com.ling.jibonetposa.entities.iot.SaveAuthDataEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioCreatePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioDeletePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioEditNameImagePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioEditPOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.config.DeviceAction;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

import static com.ling.jibonetposa.base.BaseRequestModel.ERROR_PASS_MISTAKE;
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
        findViewById(R.id.btn_ht_updatename).setOnClickListener(this);
        findViewById(R.id.btn_bl_login).setOnClickListener(this);
        findViewById(R.id.btn_he_login).setOnClickListener(this);
        findViewById(R.id.btn_he_updatename).setOnClickListener(this);
        findViewById(R.id.btn_bl_accesskey).setOnClickListener(this);
        findViewById(R.id.btn_getbrandconfigure).setOnClickListener(this);
        findViewById(R.id.btn_updatedevname).setOnClickListener(this);
        findViewById(R.id.btn_getscenarios).setOnClickListener(this);
        findViewById(R.id.btn_getiottips).setOnClickListener(this);
        findViewById(R.id.btn_getdeviceconfigure).setOnClickListener(this);
        findViewById(R.id.btn_scenarioEdit).setOnClickListener(this);
        findViewById(R.id.btn_scenarioDelete).setOnClickListener(this);
        findViewById(R.id.btn_scenarioCreate).setOnClickListener(this);
        findViewById(R.id.btn_getscenarioDev).setOnClickListener(this);
        findViewById(R.id.scenarioEditNameImage).setOnClickListener(this);
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
            case R.id.btn_updatedevname:
                updateDevName();
                break;
            case R.id.btn_getiottips:
                getIOTTips();
                break;
            case R.id.btn_getscenarios:
                getScenarios();
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
            case R.id.btn_ht_updatename:
                doHTUpdateDate();
                break;
            case R.id.btn_bl_login:
//                doBLGetAccessToken();
                doSaveAuthToServer();
                break;
            case R.id.btn_he_login:
                doHELogin();
                break;
            case R.id.btn_he_updatename:
                doHEUpdateDate();
                break;
            case R.id.btn_bl_accesskey:
                queryKey();
                break;
            case R.id.btn_getbrandconfigure:
                getBrandConfigure();
                break;
            case R.id.btn_getdeviceconfigure:
                getDevicesConfigure();
                break;
            case R.id.btn_scenarioEdit:
                scenarioEditModel();
                break;
            case R.id.btn_scenarioDelete:
                scenarioDeleteModel();
                break;
            case R.id.btn_scenarioCreate:
                scenarioCreateModel();
                break;
            case R.id.btn_getscenarioDev:
                getscenarioDev();
                break;
            case R.id.scenarioEditNameImage:
                scenarioEditNameImage();
                break;
        }
    }

    private void getscenarioDev() {
        LingManager.getInstance().getIOTAgent().scenarioGetDevicesModel("jibo", "438", new IRequestCallback() {
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

    private void scenarioEditNameImage() {
        ScenarioEditNameImagePOSTEntity scenarioEditNameImagePOSTEntity = new ScenarioEditNameImagePOSTEntity("jibo", "601", "修改名字", "0");
        LingManager.getInstance().getIOTAgent().PostScenarioEditNameImageModel(scenarioEditNameImagePOSTEntity, new IRequestCallback() {
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

    private void scenarioEditModel() {
        String userId = "jibo";
        String id = "601";
        String name = "回家场景";
        String image_type = "1";
        List<DeviceAction> devices = new ArrayList<>();
        Map<String, String> config = new HashMap<>();
        config.put("onoff", "on");
        DeviceAction deviceAction = new DeviceAction("13341", "0", config);// String device_id, String type, Map<String, String> config
        devices.add(deviceAction);
        //String userid, String id, String name, String image_type, List<DeviceAction> devices
        ScenarioEditPOSTEntity scenarioSaveAllPOSTEntity = new ScenarioEditPOSTEntity(userId, id, name, image_type, devices);

        LingManager.getInstance().getIOTAgent().scenarioEditModel(scenarioSaveAllPOSTEntity, new IRequestCallback() {
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

    private void scenarioDeleteModel() {
        String userId = "jibo";
        String id = "1212";
        ScenarioDeletePOSTEntity scenarioSaveAllPOSTEntity = new ScenarioDeletePOSTEntity(userId, id);
        LingManager.getInstance().getIOTAgent().scenarioDeleteModel(scenarioSaveAllPOSTEntity, new IRequestCallback() {
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

    private void scenarioCreateModel() {
        String userId = "jibo";
        String id = "601";
        String name = "回家场景";
        String image_type = "1";
        List<DeviceAction> devices = new ArrayList<>();
        Map<String, String> config = new HashMap<>();
        config.put("onoff", "on");
        DeviceAction deviceAction = new DeviceAction("13341", "0", config);// String device_id, String type, Map<String, String> config
        devices.add(deviceAction);
        //String userid, String id, String name, String image_type, List<DeviceAction> devices
        ScenarioCreatePOSTEntity scenarioSaveAllPOSTEntity = new ScenarioCreatePOSTEntity(userId, name, image_type, devices);

        LingManager.getInstance().getIOTAgent().scenarioCreateModel(scenarioSaveAllPOSTEntity, new IRequestCallback() {
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

    private void getScenarios() {
        LingManager.getInstance().getIOTAgent().getScenariosFromServer("jibo", new IRequestCallback() {
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

    private void getDevicesConfigure() {
        LingManager.getInstance().getIOTAgent().getDevicesConfigure(new IRequestCallback() {
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

    private void updateDevName() {
        List<PutUpdateDevNameEntity.Item> devices = new ArrayList<>();
        devices.add(new PutUpdateDevNameEntity.Item("13341", "测试灯1111"));
        PutUpdateDevNameEntity putUpdateDevNameEntity = new PutUpdateDevNameEntity("jibo", devices);
        LingManager.getInstance().getIOTAgent().updateDevName(putUpdateDevNameEntity, new IRequestCallback() {
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

    private void getIOTTips() {
        LingManager.getInstance().getIOTAgent().getIOTTips(new IRequestCallback() {
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

    private void doBLGetAccessToken() {
        String code = "d0FMG3FRTRSnMEJrR1516A";
        LingManager.getInstance().getIOTAgent().getBroadLinkAuthorized(code, new IRequestCallback() {
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

    private void checkBrand() {
        LingManager.getInstance().getIOTAgent().checkBrandStatus("jibo", new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    BrandStatusEntity brandEntity = (BrandStatusEntity) entity;
                    if (brandEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "checkBrand: brandStatusEntity" + brandEntity.toString());
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
                    DevicesEntity devicesEntity = (DevicesEntity) entity;
                    if (devicesEntity != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "getDev: devicesEntity" + devicesEntity.toString());
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
                } else if (errorCode == ERROR_PASS_MISTAKE) {

                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "doHELogin: error" + error.getMessage());
                }
            }
        });
    }

    private void doHEUpdateDate() {
        String identId = "88C25542458201000006";
        String name = "新风系统";

        LingManager.getInstance().getIOTAgent().updateHaierDevName(identId, name, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "execute: SUCCESS");
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "execute: error" + error.getMessage());
                }
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
        LingManager.getInstance().getIOTAgent().cancelAuthorized("123456", IOTDevConstant.BRAND_TYPE_PHANTOM, new IRequestCallback() {
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
        LingManager.getInstance().getIOTAgent().getTokenFromServer("jibo", IOTDevConstant.BRAND_TYPE_PHANTOM, new IRequestCallback() {
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

    private void doHTUpdateDate() {
        String access_token = "ff9b575c64c6a391d7149903a407cd08bbfd330f77c451885075a5f8fd36ebcf";
        String identifier = "N13341";
        String name = "测试灯灯灯灯灯灯灯灯";
        LingManager.getInstance().getIOTAgent().updatePhantomDevName(access_token, identifier, name, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode + "    /   " + error.getMessage());
                }
            }
        });
    }

    public void doSaveAuthToServer() {
        SaveAuthDataEntity saveAuthDataEntity = new SaveAuthDataEntity();
        saveAuthDataEntity.setUserid("123456");
        saveAuthDataEntity.setType(IOTDevConstant.BRAND_TYPE_BROADLINK);
        saveAuthDataEntity.setAccess_token("5RHXIJuDSUe3YZl56gRu1g");
        saveAuthDataEntity.setRefresh_token("Ix7QHK6PR9CUvGe8L9Ozhw");
        saveAuthDataEntity.setExpires_in(123123123L);
        saveAuthDataEntity.setScope("123123123");
        saveAuthDataEntity.setCreated_at(123123123L);
        saveAuthDataEntity.setToken_type("bearer");

        LingManager.getInstance().getIOTAgent().doSaveAuthDataToServer(saveAuthDataEntity, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity baseEntity, int code, Throwable error) {
                if (code == RETROFIT_SUCCESS) {
                    ResultSaveAuthDataEntity tokenEntity = (ResultSaveAuthDataEntity) baseEntity;
                    if (tokenEntity != null) {
                        if (tokenEntity.getErrmsg().equals("success")) {
                            LingManager.getInstance().getLingLog().LOGD(TAG, "success");
                            return;
                        }
                    }
                    LingManager.getInstance().getLingLog().LOGD(TAG, "result null");
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "error： " + error.toString());
                }
            }
        });
    }

    private void queryKey() {
        LingManager.getInstance().getIOTAgent().queryBLKey(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode + "    /   " + error.getMessage());
                }
            }
        });
    }


    private void nameChecking() {
    }

    private void getBrandConfigure() {
        LingManager.getInstance().getIOTAgent().getBrandConfigure(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode + "    /   " + error.getMessage());
                }
            }
        });
    }


}
