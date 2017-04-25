package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.BrandBean;
import com.ling.jibonetposa.entities.BrandStatusEntity;
import com.ling.jibonetposa.entities.DevicesEntity;
import com.ling.jibonetposa.entities.ResultGetBrandEntity;
import com.ling.jibonetposa.entities.ResultGetDevicesEntity;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.broadlink.sdk.interfaces.controller.BLDeviceScanListener;

import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;
import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_REDIRECT_URI;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_SCOPE;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_BROADLINK;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_HAIER;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_PHANTOM;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_BROADLINK;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_HAIER;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_PHANTOM;

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
        mGetDevicesFromServerModel = new GetDevicesFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetDevicesEntity brandEntity = (ResultGetDevicesEntity) entity;
                    if (brandEntity != null) {
                        DevicesEntity devicesEntity = LingManager.getInstance().getIOTAgent().getDevicesEntity(userid, brandEntity);
                        requestCallback.responsedCallback(devicesEntity, errorCode, error);
                    }
                } else {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }

            }
        });
        mGetDevicesFromServerModel.getDevices(userid, type);
    }

    /**
     * 检查品牌的绑定信息
     *
     * @param userid
     */
    public void checkBrandStatus(final String userid, final IRequestCallback requestCallback) {
        if (mCheckBrandsFromServerModel != null) {
            mCheckBrandsFromServerModel.cancel();
            mCheckBrandsFromServerModel = null;
        }
        mCheckBrandsFromServerModel = new CheckBrandsFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetBrandEntity brandEntity = (ResultGetBrandEntity) entity;
                    if (brandEntity != null) {
                        BrandStatusEntity brandStatusEntity = LingManager.getInstance().getIOTAgent().getBrandStatusEntity(brandEntity);
                        brandStatusEntity.setUserid(userid);
                        requestCallback.responsedCallback(brandStatusEntity, errorCode, error);
                    }
                } else {
                    requestCallback.responsedCallback(getDefaultBrandStatus(), errorCode, error);
                }
            }
        });
        mCheckBrandsFromServerModel.getBrands(userid);
    }

    /**
     * 取消用户授权
     */
    public void cancelAuthorized(String userid, String brandType, final IRequestCallback requestCallback) {
        if (mCancelAuthorizedModel != null) {
            mCancelAuthorizedModel.cancel();
            mCancelAuthorizedModel = null;
        }
        mCancelAuthorizedModel = new CancelAuthorizedModel(requestCallback);
        mCancelAuthorizedModel.cancelAuthorized(userid, brandType);
    }

    /**
     * 获取保存的Phantom Token
     */
    public void getTokenFromServer(String userId, String brandType, final IRequestCallback requestCallback) {
        if (mGetTokenFromServerModel != null) {
            mGetTokenFromServerModel.cancel();
            mGetTokenFromServerModel = null;
        }
        mGetTokenFromServerModel = new GetTokenFromServerModel(requestCallback);
        mGetTokenFromServerModel.getPhantomToken(userId, brandType);
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
    public void doSaveAuthDataToServer(SaveAuthDataEntity tokenEntity, final IRequestCallback requestCallback) {
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

    public BrandStatusEntity getBrandStatusEntity(ResultGetBrandEntity getBrandEntity) {
        BrandStatusEntity defaultBrandStatus = getDefaultBrandStatus();
        for (BrandBean brandBean : getBrandEntity.getData()) {
            for (BrandStatusEntity.Brand brand : defaultBrandStatus.getBrand_list()) {
                if (brandBean.getKey().equals(brand.getBrand_id())) {
                    brand.setBrand_status(1);
                }
            }
        }
        return doSoreList(defaultBrandStatus);
    }

    public DevicesEntity getDevicesEntity(String userid, ResultGetDevicesEntity getBrandEntity) {
        DevicesEntity devicesEntity = new DevicesEntity();
        devicesEntity.setUserid(userid);
        devicesEntity.setBrand_list(getBrandEntity.getData());
        return devicesEntity;
    }

    /**
     * 对品牌列表排序，将已绑定的品牌放在集合最前面
     */
    public BrandStatusEntity doSoreList(BrandStatusEntity brandStatusEntity) {
        Comparator<BrandStatusEntity.Brand> comparator = new Comparator<BrandStatusEntity.Brand>() {
            @Override
            public int compare(BrandStatusEntity.Brand brand1, BrandStatusEntity.Brand brand2) {
                if (brand1.getBrand_status() != brand2.getBrand_status()) {
                    // 以品牌的绑定状态排序，已绑定的品牌在List前面
                    return brand2.getBrand_status() - brand1.getBrand_status();
                } else if (!brand1.getBrand_id().equals(brand2.getBrand_id())) {
                    // 以品牌的名称排序
                    return brand1.getBrand_name().compareTo(brand2.getBrand_name());
                } else {
                    // 以品牌的id排序
                    return brand1.getBrand_id().compareTo(brand2.getBrand_id());
                }
            }
        };
        Collections.sort(brandStatusEntity.getBrand_list(), comparator);
        return brandStatusEntity;
    }


    /**
     * 获取在App上显示的品牌列表
     */
    public BrandStatusEntity getDefaultBrandStatus() {
        BrandStatusEntity statusEntity = new BrandStatusEntity();
        List<BrandStatusEntity.Brand> brandList = new ArrayList<>();

        BrandStatusEntity.Brand brand1 = new BrandStatusEntity.Brand(BRAND_TYPE_PHANTOM, BRAND_NAME_PHANTOM, 0);
        BrandStatusEntity.Brand brand2 = new BrandStatusEntity.Brand(BRAND_TYPE_HAIER, BRAND_NAME_HAIER, 0);
        BrandStatusEntity.Brand brand3 = new BrandStatusEntity.Brand(BRAND_TYPE_BROADLINK, BRAND_NAME_BROADLINK, 0);
//        BrandStatusEntity.Brand brand4 = new BrandStatusEntity.Brand(BRAND_TYPE_XIAOMI, BRAND_NAME_XIAOMI, 0);
//        BrandStatusEntity.Brand brand5 = new BrandStatusEntity.Brand(BRAND_TYPE_MEIDI, BRAND_NAME_MEIDI, 0);
//        BrandStatusEntity.Brand brand6 = new BrandStatusEntity.Brand(BRAND_TYPE_AUX, BRAND_NAME_AUX, 0);

        brandList.add(brand1);
        brandList.add(brand2);
        brandList.add(brand3);
//        brandList.add(brand4);
//        brandList.add(brand5);
//        brandList.add(brand6);
        statusEntity.setBrand_list(brandList);
        return statusEntity;
    }

}