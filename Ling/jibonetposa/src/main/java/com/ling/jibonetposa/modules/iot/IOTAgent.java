package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedCodeEntity;
import com.ling.jibonetposa.entities.PhantomAccessTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.broadlink.BLDevicesModel;
import com.ling.jibonetposa.models.broadlink.BLLoginModel;
import com.ling.jibonetposa.models.haier.HEModel;
import com.ling.jibonetposa.models.phantom.IOTCancelAuthorizedModel;
import com.ling.jibonetposa.models.phantom.IOTGetDevicesFromPhantomModel;
import com.ling.jibonetposa.models.phantom.IOTGetPhantomTokenModel;
import com.ling.jibonetposa.models.phantom.IOTGetTokenFromServerModel;
import com.ling.jibonetposa.models.phantom.IOTSaveTokenToServerModel;
import com.ling.jibonetposa.utils.NetWorkUtil;

import java.util.HashMap;
import java.util.Map;

import cn.com.broadlink.sdk.data.controller.BLDNADevice;
import cn.com.broadlink.sdk.interfaces.controller.BLDeviceScanListener;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_REDIRECT_URI;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_SCOPE;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTAgent {

    private BLDevicesModel mBLDevicesModel = new BLDevicesModel();
    private BLLoginModel mBLLoginModel = new BLLoginModel();

    // ==================================== Phantom 幻腾 ==================================

    /**
     * 获取幻腾授权
     * <p>
     * 根据authorizedCode去获取幻腾Token，然后将Token保存到服务器
     *
     * @param authorizedEntity 获取授权所需要的参数
     */
    public void getPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
        new IOTGetPhantomTokenModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    PhantomAccessTokenEntity tokenEntity = (PhantomAccessTokenEntity) entity;
                    doSavePhantomTokenToServer(authorizedEntity.getUserId(), tokenEntity, requestCallback);
                } else {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).getPhantomToken(authorizedEntity.getAuthorizedCode());
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getPhantomTokenFromServer(String userId, final IRequestCallback requestCallback) {
        new IOTGetTokenFromServerModel(requestCallback).getPhantomToken(userId);
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getPhantomDevicesFromPhantom(String accessToken, final IRequestCallback requestCallback) {
        new IOTGetDevicesFromPhantomModel(requestCallback).getPhantomDevices(accessToken);
    }

    /**
     * 将Token保存
     */
    private void doSavePhantomTokenToServer(String userId, PhantomAccessTokenEntity tokenEntity, final IRequestCallback requestCallback) {
        new IOTSaveTokenToServerModel(requestCallback).savePhantomToken(userId, tokenEntity);
    }

    /**
     * 取消幻腾授权
     */
    public void cancelPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
        new IOTCancelAuthorizedModel(requestCallback).cancelAuthorized(authorizedEntity.getUserId());
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

    // ==================================== BroadLink 博联 ==================================

    /**
     * 开始设备扫描
     */
    public void doBLStartProbe() {
        mBLDevicesModel.startProbe();
    }

    /**
     * 停止设备扫描
     */
    public void doBLStopProbe() {
        mBLDevicesModel.stopProbe();
    }

    /**
     * 设置设备监听
     */
    public void setBLOnDeviceScanListener(BLDeviceScanListener scanListener) {
        mBLDevicesModel.setOnDeviceScanListener(new BLDeviceScanListener() {
            @Override
            public void onDeviceUpdate(BLDNADevice bldnaDevice, boolean b) {

            }
        });
    }

    /**
     * 登录博联账号
     *
     * @param userName 用户名
     * @param password 密码
     */
    public void doBLLogin(String userName, String password, BLLoginModel.BLTaskListener blTaskListener) {
        mBLLoginModel.doLogin(userName, password, blTaskListener);
    }

    /**
     * 第三方授权登陆
     *
     * @param thirdId 第三方授权ID
     */
    public void doBLThirdAuth(String thirdId, BLLoginModel.BLTaskListener blTaskListener) {
        mBLLoginModel.doThirdAuth(thirdId, blTaskListener);
    }

    /**
     * 注册博联账号
     *
     * @param countryCode 国家码“+86”
     * @param phone       电话号码
     * @param vCode       验证码
     * @param password    密码
     * @param nickName    名称
     */
    public void doBLRegist(String countryCode, String phone, String vCode, String password, String nickName, BLLoginModel.BLTaskListener blTaskListener) {
        mBLLoginModel.doRegist(countryCode, phone, vCode, password, nickName, blTaskListener);
    }

    /**
     * 获取手机验证码
     *
     * @param countryCode 国家码“+86”
     * @param phone       电话号码
     */
    public void doBLGetVCode(String countryCode, String phone, BLLoginModel.BLTaskListener blTaskListener) {
        mBLLoginModel.doGetVCode(countryCode, phone, blTaskListener);
    }

    // ==================================== Haier 海尔 ==================================

    public void doHELogin(String username, String password, HEModel.HEListener heListener) {

    }

    private void getDevicesOfAccount(final HEModel.HEListener heListener) {

    }

}