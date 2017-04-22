package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.entities.SaveAuthDataEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.iot.CancelAuthorizedModel;
import com.ling.jibonetposa.models.iot.CheckBrandsFromServerModel;
import com.ling.jibonetposa.models.iot.GetDevicesFromServerModel;
import com.ling.jibonetposa.models.iot.GetTokenFromServerModel;
import com.ling.jibonetposa.models.iot.SaveTokenToServerModel;
import com.ling.jibonetposa.models.iot.broadlink.BLDevicesModel;
import com.ling.jibonetposa.models.iot.broadlink.BLLoginModel;
import com.ling.jibonetposa.models.iot.haier.HEModel;
import com.ling.jibonetposa.models.iot.phantom.GetPhantomTokenModel;
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

    private BLDevicesModel mBLDevicesModel = new BLDevicesModel();
    private BLLoginModel mBLLoginModel = new BLLoginModel();
    private HEModel mHEModel;

    private GetDevicesFromServerModel mGetDevicesFromServerModel;
    private CheckBrandsFromServerModel mCheckBrandsFromServerModel;
    private CancelAuthorizedModel mCancelAuthorizedModel;
    private GetTokenFromServerModel mGetTokenFromServerModel;
    private SaveTokenToServerModel mSaveTokenToServerModel;
    private GetPhantomTokenModel mGetPhantomTokenModel;

    public void shutdownResponsed() {
        if (mGetDevicesFromServerModel != null) {
            mGetDevicesFromServerModel.cancel();
            mGetDevicesFromServerModel = null;
        }
        if (mCheckBrandsFromServerModel != null) {
            mCheckBrandsFromServerModel.cancel();
            mCheckBrandsFromServerModel = null;
        }
        if (mCancelAuthorizedModel != null) {
            mCancelAuthorizedModel.cancel();
            mCancelAuthorizedModel = null;
        }
        if (mGetTokenFromServerModel != null) {
            mGetTokenFromServerModel.cancel();
            mGetTokenFromServerModel = null;
        }
        if (mSaveTokenToServerModel != null) {
            mSaveTokenToServerModel.cancel();
            mSaveTokenToServerModel = null;
        }
        if (mGetPhantomTokenModel != null) {
            mGetPhantomTokenModel.cancel();
            mGetPhantomTokenModel = null;
        }
    }

    /**
     * 获取所有设备
     *
     * @param userid 用户id
     * @param type   刷新的类别，（0：获取存储在Ling服务器的设备信息。1：获取所有第三方最新的设备信息）
     */
    public void getAllDevices(final String userid, int type, final IRequestCallback requestCallback) {
        if (mGetDevicesFromServerModel != null) {
            mGetDevicesFromServerModel.cancel();
            mGetDevicesFromServerModel = null;
        }
        mGetDevicesFromServerModel = new GetDevicesFromServerModel(requestCallback);
        mGetDevicesFromServerModel.getDevices(userid, type);
    }

    /**
     * 检查品牌的绑定信息
     *
     * @param userid
     */
    public void checkBrandStatus(String userid, final IRequestCallback requestCallback) {
        if (mCheckBrandsFromServerModel != null) {
            mCheckBrandsFromServerModel.cancel();
            mCheckBrandsFromServerModel = null;
        }
        mCheckBrandsFromServerModel = new CheckBrandsFromServerModel(requestCallback);
        mCheckBrandsFromServerModel.getBrands(userid);
    }

    /**
     * 取消用户授权
     */
    public void cancelAuthorized(String userid, int brandtype, final IRequestCallback requestCallback) {
        if (mCancelAuthorizedModel != null) {
            mCancelAuthorizedModel.cancel();
            mCancelAuthorizedModel = null;
        }
        mCancelAuthorizedModel = new CancelAuthorizedModel(requestCallback);
        mCancelAuthorizedModel.cancelAuthorized(userid, brandtype);
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getTokenFromServer(String userId, String type, final IRequestCallback requestCallback) {
        if (mGetTokenFromServerModel != null) {
            mGetTokenFromServerModel.cancel();
            mGetTokenFromServerModel = null;
        }
        mGetTokenFromServerModel = new GetTokenFromServerModel(requestCallback);
        mGetTokenFromServerModel.getPhantomToken(userId, type);
    }


    /**
     * 登录海尔账号  成功后将账号数据存到server
     */
    public void doHELogin(String username, String password, final IRequestCallback requestCallback) {
        mHEModel = new HEModel(LingManager.getInstance().getAppContext());
        mHEModel.doHELogin(username, password, requestCallback);
    }

    /**
     * 获取幻腾授权
     * <p>
     * 根据authorizedCode去获取幻腾Token，然后将Token保存到服务器
     */
    public void getPhantomAuthorized(final String authorizedCode, final IRequestCallback requestCallback) {
        if (mGetPhantomTokenModel != null) {
            mGetPhantomTokenModel.cancel();
            mGetPhantomTokenModel = null;
        }
        mGetPhantomTokenModel = new GetPhantomTokenModel(requestCallback);
        mGetPhantomTokenModel.getPhantomToken(authorizedCode);
    }

    /**
     * 保存授权数据
     */
    private void doSaveAuthDataToServer(SaveAuthDataEntity tokenEntity, final IRequestCallback requestCallback) {
        if (mSaveTokenToServerModel != null) {
            mSaveTokenToServerModel.cancel();
            mSaveTokenToServerModel = null;
        }
        mSaveTokenToServerModel = new SaveTokenToServerModel(requestCallback);
        mSaveTokenToServerModel.saveToken(tokenEntity);
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
     * 获取保存的Phantom Token
     */
    public void getPhantomDevicesFromPhantom(String accessToken, final IRequestCallback requestCallback) {
//        isStop5 = false;
//        new GetDevicesFromPhantomModel(new IRequestCallback() {
//            @Override
//            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
//                if (!isStop5) {
//                    requestCallback.responsedCallback(entity, errorCode, error);
//                }
//            }
//        }).getPhantomDevices(accessToken);
    }

}