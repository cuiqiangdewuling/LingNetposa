package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedCodeEntity;
import com.ling.jibonetposa.entities.SaveTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.iot.CheckBrandsStatusModel;
import com.ling.jibonetposa.models.iot.GetDevicesModel;
import com.ling.jibonetposa.models.iot.broadlink.BLDevicesModel;
import com.ling.jibonetposa.models.iot.broadlink.BLLoginModel;
import com.ling.jibonetposa.models.iot.haier.HEModel;
import com.ling.jibonetposa.models.iot.phantom.CancelAuthorizedModel;
import com.ling.jibonetposa.models.iot.phantom.GetDevicesFromPhantomModel;
import com.ling.jibonetposa.models.iot.phantom.GetPhantomTokenModel;
import com.ling.jibonetposa.models.iot.phantom.GetTokenFromServerModel;
import com.ling.jibonetposa.models.iot.phantom.SaveTokenToServerModel;
import com.ling.jibonetposa.utils.NetWorkUtil;

import java.util.HashMap;
import java.util.Map;

import cn.com.broadlink.sdk.interfaces.controller.BLDeviceScanListener;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_REDIRECT_URI;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_SCOPE;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTAgent {

    public boolean isStop1 = true;
    public boolean isStop2 = true;
    public boolean isStop3 = true;
    public boolean isStop4 = true;
    public boolean isStop5 = true;
    public boolean isStop6 = true;
    public boolean isStop7 = true;

    private BLDevicesModel mBLDevicesModel = new BLDevicesModel();
    private BLLoginModel mBLLoginModel = new BLLoginModel();

    public void shutdownResponsed() {
        isStop1 = true;
        isStop2 = true;
        isStop3 = true;
        isStop4 = true;
        isStop5 = true;
        isStop6 = true;
        isStop7 = true;
    }


    /**
     * 获取所有设备
     *
     * @param userid 用户id
     * @param type   刷新的类别，（0：获取存储在Ling服务器的设备信息。1：获取所有第三方最新的设备信息）
     */
    public void getAllDevices(String userid, int type, final IRequestCallback requestCallback) {
        isStop1 = false;
        new GetDevicesModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop1) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).getDevices(userid);
    }

    /**
     * 检查品牌的绑定信息
     *
     * @param userid
     */
    public void checkBrandStatus(String userid, final IRequestCallback requestCallback) {
        isStop2 = false;
        new CheckBrandsStatusModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop2) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).checkBrandStatus(userid);
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getPhantomTokenFromServer(String userId, final IRequestCallback requestCallback) {
        isStop3 = false;
        new GetTokenFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop3) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).getPhantomToken(userId);
    }

    /**
     * 获取幻腾授权
     * <p>
     * 根据authorizedCode去获取幻腾Token，然后将Token保存到服务器
     *
     * @param authorizedEntity 获取授权所需要的参数
     */
    public void getPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
        isStop4 = false;
        new GetPhantomTokenModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    SaveTokenEntity tokenEntity = (SaveTokenEntity) entity;
                    if (!isStop4) {
                        doSaveAuthorizedTokenToServer(authorizedEntity.getUserId(), tokenEntity, requestCallback);
                    }
                } else {
                    if (!isStop4) {
                        requestCallback.responsedCallback(entity, errorCode, error);
                    }
                }
            }
        }).getPhantomToken(authorizedEntity.getAuthorizedCode());
    }

    /**
     * 将Token保存
     */
    private void doSaveAuthorizedTokenToServer(String userId, SaveTokenEntity tokenEntity, final IRequestCallback requestCallback) {
        new SaveTokenToServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop4) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).saveToken(userId, tokenEntity);
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getPhantomDevicesFromPhantom(String accessToken, final IRequestCallback requestCallback) {
        isStop5 = false;
        new GetDevicesFromPhantomModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop5) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).getPhantomDevices(accessToken);
    }

    /**
     * 取消幻腾授权
     */
    public void cancelPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
        isStop6 = false;
        new CancelAuthorizedModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop6) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).cancelAuthorized(authorizedEntity.getUserId());
    }

    /**
     * 合成授权页面的请求地址
     */
    public String getPhantomAuthorizedUrl() {
        Map<String, Object> mParams = new HashMap<String, Object>();
        mParams.put("client_id", PHANTON_APP_ID);
        mParams.put("redirect_uri", PHANTON_REDIRECT_URI);
        mParams.put("response_type", "code");
        mParams.put("scope", PHANTON_SCOPE);//PHANTON_SCOPE
        return API_PATH_PHANTON_AUTHORIZE + NetWorkUtil.organizeParams(mParams);
    }

    /**
     * 登录博联账号
     *
     * @param userName 用户名
     * @param password 密码
     */
    public void doBLLogin(String userName, String password, IRequestCallback requestCallback) {
//        mBLLoginModel.doLogin(userName, password, new IRequestCallback() {
//            @Override
//            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
//
//            }
//        });
    }

    /**
     * 第三方授权登陆博联
     *
     * @param thirdId 第三方授权ID
     */
    public void doBLThirdAuth(String thirdId, BLLoginModel.BLTaskListener blTaskListener) {
//        mBLLoginModel.doThirdAuth(thirdId, blTaskListener);
    }

    /**
     * 注册博联账号
     *
     * @param countryCode 国家码“+86”
     * @param phone       电话号码
     * @param vCode       手机验证码
     * @param password    密码
     * @param nickName    名称
     */
    public void doBLRegist(String countryCode, String phone, String vCode, String password, String nickName, BLLoginModel.BLTaskListener blTaskListener) {
//        mBLLoginModel.doRegist(countryCode, phone, vCode, password, nickName, blTaskListener);
    }

    /**
     * 获取博联手机验证码
     *
     * @param countryCode 国家码“+86”
     * @param phone       电话号码
     */
    public void doBLGetVCode(String countryCode, String phone, BLLoginModel.BLTaskListener blTaskListener) {
//        mBLLoginModel.doGetVCode(countryCode, phone, blTaskListener);
    }

    /**
     * 开始博联设备扫描
     */
    public void doBLStartProbe() {
//        mBLDevicesModel.startProbe();
    }

    /**
     * 停止博联设备扫描
     */
    public void doBLStopProbe() {
//        mBLDevicesModel.stopProbe();
    }

    /**
     * 设置博联设备监听
     */
    public void setBLOnDeviceScanListener(BLDeviceScanListener scanListener) {
//        mBLDevicesModel.setOnDeviceScanListener(new BLDeviceScanListener() {
//            @Override
//            public void onDeviceUpdate(BLDNADevice bldnaDevice, boolean b) {
//
//            }
//        });
    }

    /**
     * 登录海尔账号
     */
    public void doHELogin(String username, String password, final IRequestCallback requestCallback) {
        isStop7 = false;
        new HEModel(LingManager.getInstance().getAppContext()).doHELogin(username, password, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (!isStop7) {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        });
    }
}