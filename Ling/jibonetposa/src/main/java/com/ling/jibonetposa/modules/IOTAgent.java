package com.ling.jibonetposa.modules;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.bean.BrandBean;
import com.ling.jibonetposa.entities.bean.DeviceBean;
import com.ling.jibonetposa.entities.iot.BrandStatusEntity;
import com.ling.jibonetposa.entities.iot.DevicesEntity;
import com.ling.jibonetposa.entities.iot.PutUpdateDevNameEntity;
import com.ling.jibonetposa.entities.iot.ResultGetBrandEntity;
import com.ling.jibonetposa.entities.iot.ResultGetDevicesEntity;
import com.ling.jibonetposa.entities.iot.ResultGetScenariosEntity;
import com.ling.jibonetposa.entities.iot.SaveAuthDataEntity;
import com.ling.jibonetposa.entities.iot.ScenariosListEntity;
import com.ling.jibonetposa.entities.iot.scenario.ResultDevicesConfigureEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioCreatePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioDeletePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioEditNameImagePOSTEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioEditPOSTEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.GetTipsModel;
import com.ling.jibonetposa.models.iot.brand.broadlink.GetBroadLinkTokenModel;
import com.ling.jibonetposa.models.iot.brand.broadlink.UpdataBLGetAccessKeyModel;
import com.ling.jibonetposa.models.iot.brand.haier.HEModel;
import com.ling.jibonetposa.models.iot.brand.phantom.GetPhantomTokenModel;
import com.ling.jibonetposa.models.iot.brand.phantom.UpdatePhantomNameModel;
import com.ling.jibonetposa.models.iot.devices.CheckBrandsFromServerModel;
import com.ling.jibonetposa.models.iot.devices.GetDevicesFromServerModel;
import com.ling.jibonetposa.models.iot.devices.UpdateDevNameModel;
import com.ling.jibonetposa.models.iot.oauth.CancelAuthorizedModel;
import com.ling.jibonetposa.models.iot.oauth.GetBrandOAuthConfigureModel;
import com.ling.jibonetposa.models.iot.oauth.GetTokenFromServerModel;
import com.ling.jibonetposa.models.iot.oauth.SaveTokenToServerModel;
import com.ling.jibonetposa.models.iot.scenarios.GetDevicesConfigureModel;
import com.ling.jibonetposa.models.iot.scenarios.GetScenariosDevicesModel;
import com.ling.jibonetposa.models.iot.scenarios.GetScenariosFromServerModel;
import com.ling.jibonetposa.models.iot.scenarios.PostScenarioCreateModel;
import com.ling.jibonetposa.models.iot.scenarios.PostScenarioDeleteModel;
import com.ling.jibonetposa.models.iot.scenarios.PostScenarioEditModel;
import com.ling.jibonetposa.models.iot.scenarios.PostScenarioEditNameImageModel;
import com.ling.jibonetposa.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;
import static com.ling.jibonetposa.constants.APIConstant.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.APIConstant.BROADLINK_API_PATH;
import static com.ling.jibonetposa.constants.APIConstant.BROADLINK_CLIENT_ID;
import static com.ling.jibonetposa.constants.APIConstant.OAUTH_REDIRECT_URI;
import static com.ling.jibonetposa.constants.APIConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.APIConstant.PHANTON_SCOPE;
import static com.ling.jibonetposa.constants.IOTDevConstant.REGEX_NAME;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTAgent {

    // ============= 设备相关 ====================

    /**
     * 获取所有设备
     *
     * @param userid 用户id
     * @param type   刷新的类别，（0：获取存储在Ling服务器的设备信息。1：获取所有第三方最新的设备信息）
     */
    public void getAllDevices(String userid, int type, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        final String finalUserid = userid;
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + finalUserid);
        new GetDevicesFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetDevicesEntity brandEntity = (ResultGetDevicesEntity) entity;
                    if (brandEntity != null) {
                        DevicesEntity devicesEntity = getDevicesEntity(finalUserid, brandEntity);
                        checkDevicesName(devicesEntity);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(devicesEntity, errorCode, error);
                    }
                } else {
                    if (requestCallback != null)
                        requestCallback.responsedCallback(entity, errorCode, error);
                }

            }
        }).getDevices(userid, type);
    }

    /**
     * 根据网络请求回来的数据，整理成我们需要的数据
     */
    public DevicesEntity getDevicesEntity(String userid, ResultGetDevicesEntity getBrandEntity) {
        DevicesEntity devicesEntity = new DevicesEntity();
        devicesEntity.setUserid(userid);
        devicesEntity.setBrand_list(getBrandEntity.getData());
        return devicesEntity;
    }

    /**
     * 修改设备名称列表
     */
    public void updateDevName(PutUpdateDevNameEntity putUpdateNameEntity, IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            putUpdateNameEntity.getData().getAttributes().setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + putUpdateNameEntity.getData().getAttributes().getUserid());
        new UpdateDevNameModel(requestCallback).execute(putUpdateNameEntity);
    }

    /**
     * 检查品牌的绑定信息
     *
     * @param userid
     */
    public void checkBrandStatus(String userid, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        final String finalUserid = userid;
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + finalUserid);
        new CheckBrandsFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetBrandEntity brandEntity = (ResultGetBrandEntity) entity;
                    if (brandEntity != null) {
                        BrandStatusEntity brandStatusEntity = getBrandStatusEntity(brandEntity);
                        brandStatusEntity.setUserid(finalUserid);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(brandStatusEntity, errorCode, error);
                    } else {
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, errorCode, error);
                    }
                } else {
                    if (requestCallback != null)
                        requestCallback.responsedCallback(null, errorCode, error);
                }
            }
        }).getBrands(userid);
    }

    /**
     * 根据网络请求回来的数据，整理成我们需要的数据
     */
    public BrandStatusEntity getBrandStatusEntity(ResultGetBrandEntity getBrandEntity) {
        BrandStatusEntity brandStatusEntity = new BrandStatusEntity();
        List<BrandStatusEntity.Brand> brand_list = new ArrayList<>();
        for (BrandBean brandBean : getBrandEntity.getData()) {
            brand_list.add(new BrandStatusEntity.Brand(brandBean.getKey(), brandBean.getName(), brandBean.getCode()));
        }
        brandStatusEntity.setBrand_list(brand_list);
        return doSoreBrandList(brandStatusEntity);
    }


    /**
     * 获取到IOT的TipList
     */
    public void getIOTTips(IRequestCallback requestCallback) {
        new GetTipsModel(requestCallback).getIOTTip(1);
    }

    // ============= 授权相关 ====================

    /**
     * 取消用户授权
     */
    public void cancelAuthorized(String userid, String brandType, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + userid);
        new CancelAuthorizedModel(requestCallback).cancelAuthorized(userid, brandType);
    }

    /**
     * 获取保存的Token
     */
    public void getTokenFromServer(String userid, String brandType, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + userid);
        new GetTokenFromServerModel(requestCallback).getPhantomToken(userid, brandType);
    }

    /**
     * 登录海尔账号  成功后将账号数据存到server
     */
    public void doHELogin(String username, String password, final IRequestCallback requestCallback) {
        new HEModel(LingManager.getInstance().getAppContext()).doHELogin(username, password, requestCallback);
    }

    /**
     * 获取幻腾授权
     * <p>
     * 根据authorizedCode去获取幻腾Token，然后将Token保存到服务器
     */
    public void getPhantomAuthorized(final String authorizedCode, final IRequestCallback requestCallback) {
        new GetPhantomTokenModel(requestCallback).getPhantomToken(authorizedCode);
    }

    /**
     * 获取BroadLink授权
     * <p>
     * 根据authorizedCode去获取BroadLink Token，然后将Token保存到服务器
     */
    public void getBroadLinkAuthorized(final String authorizedCode, final IRequestCallback requestCallback) {
        new GetBroadLinkTokenModel(requestCallback).getBroadLinkToken(authorizedCode);
    }

    /**
     * 保存授权数据
     */
    public void doSaveAuthDataToServer(SaveAuthDataEntity tokenEntity, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            tokenEntity.setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + tokenEntity.getUserid());
        new SaveTokenToServerModel(requestCallback).saveToken(tokenEntity);
    }

    /**
     * 对品牌列表排序，将已绑定的品牌放在集合最前面
     */
    public BrandStatusEntity doSoreBrandList(BrandStatusEntity brandStatusEntity) {
        Comparator<BrandStatusEntity.Brand> comparator = new Comparator<BrandStatusEntity.Brand>() {
            @Override
            public int compare(BrandStatusEntity.Brand brand1, BrandStatusEntity.Brand brand2) {
                // 以品牌的绑定状态排序，已绑定的品牌在List前面
                if (brand1.getBrand_status() != brand2.getBrand_status()) {
                    return brand1.getBrand_status() - brand2.getBrand_status();
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
     * 合成幻腾授权页面的请求地址
     */
    public String getPhantomAuthorizedUrl() {
        Map<String, Object> mParams = new HashMap<String, Object>();
        mParams.put("client_id", PHANTON_APP_ID);
        mParams.put("redirect_uri", OAUTH_REDIRECT_URI);
        mParams.put("response_type", "code");
        mParams.put("scope", PHANTON_SCOPE);//PHANTON_SCOPE
        return API_PATH_PHANTON_AUTHORIZE + NetWorkUtil.organizeParams(mParams);
    }

    /**
     * 合成BroadLink授权页面的请求地址
     */
    public String getBroadLinkAuthorizedUrl() {
        Map<String, Object> mParams = new HashMap<String, Object>();
        mParams.put("client_id", BROADLINK_CLIENT_ID);
        mParams.put("redirect_uri", OAUTH_REDIRECT_URI);
        mParams.put("response_type", "code");
        return BROADLINK_API_PATH + NetWorkUtil.organizeParams(mParams);
    }

    /**
     * 获取各品牌授权所需的数据、基本参数
     */
    public void getBrandConfigure(IRequestCallback requestCallback) {
        new GetBrandOAuthConfigureModel(requestCallback).getBrandConfigure();
    }

    // ============= 名字查重相关 ======

    /**
     * 检查设备是否重名，并返回重名的设备列表
     *
     * @param devicesEntity
     */
    public void checkDevicesName(DevicesEntity devicesEntity) {
        if (devicesEntity == null || devicesEntity.getBrand_list() == null || !(devicesEntity.getBrand_list().size() > 0))
            return;
        List<DeviceBean> nameRepeatDevList = new ArrayList<>(); // 名字重复的数据
        List<DeviceBean> devDataForQueryList = new ArrayList<>();// 要进行 名字查重 的数据
        List<DeviceBean> nameNotFitRulesDevList = new ArrayList<>();// 不符合规则的名字（名字不被NLU识别）

        for (BrandBean brandBean : devicesEntity.getBrand_list()) {
            if (brandBean.getVal() != null && brandBean.getVal().size() > 0) {
                for (DeviceBean deviceBean : brandBean.getVal()) {
                    deviceBean.setBrand_id(brandBean.getKey());
                    deviceBean.setBrand_name(brandBean.getName());
                    deviceBean.setBrand_status(brandBean.getCode());
                    if (brandBean.getCode() == 0) {  //授权状态正常时，才进行名字查重

                        // 拿到每一个设备，单独进行判断
                        if (match(REGEX_NAME, deviceBean.getDevice_name())) {
                            // 设备名符合规则 才进行查重
                            deviceBean.setDevice_code(0);
                            devDataForQueryList.add(deviceBean);
                        } else {
                            // 设备名不符合规则
                            deviceBean.setDevice_code(-1);
                            nameNotFitRulesDevList.add(deviceBean);
                        }
                    }
                }
            }
        }
        a:
        for (int i = 0; i < devDataForQueryList.size(); i++) {
            b:
            for (int j = 0; j < devDataForQueryList.size(); j++) {
                if (i != j) {
                    boolean equals = devDataForQueryList.get(i).getDevice_name().equals(devDataForQueryList.get(j).getDevice_name());
                    if (equals) {
                        nameRepeatDevList.add(devDataForQueryList.get(i));
                        break b;
                    }
                }
            }
        }
        devicesEntity.setRepeatDev(nameRepeatDevList);// 保存重名的设备集合
        devicesEntity.setNameNotFitRulesDevList(nameNotFitRulesDevList);// 保存不符合命名规则的设备集合
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 针对重名的设备，生成一个随机的不重名的设备（原名字+数字）
     */
    public List<PutUpdateDevNameEntity.Item> getRenameList(DevicesEntity devicesEntity) {
        List<PutUpdateDevNameEntity.Item> items = new ArrayList<>();
        List<DeviceBean> repeatDev = devicesEntity.getRepeatDev();

        for (int i = 0; i < repeatDev.size(); i++) {
            int flag = 1;
            if (repeatDev.get(i).getBrand_status() == 0) {
                String name = repeatDev.get(i).getDevice_name() + flag++;
                while (hasSameName(name, devicesEntity) || hasSameName(name, items)) {
                    name = repeatDev.get(i).getDevice_name() + flag++;
                }
                PutUpdateDevNameEntity.Item item = new PutUpdateDevNameEntity.Item(repeatDev.get(i).getDevice_id(), name);
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 判断批量改名接口传入的数据是否有重名
     */
    public boolean hasSameName(String devName, List<PutUpdateDevNameEntity.Item> items) {
        if (items != null && (items.size() > 0))
            for (PutUpdateDevNameEntity.Item item : items) {
                if (devName.equals(item.getDevice_name())) {
                    return true;
                }
            }
        return false;
    }

    /**
     * 判断所有的设备列表中，是否有重名
     */
    public boolean hasSameName(String devName, DevicesEntity devicesEntity) {
        if (devicesEntity != null && devicesEntity.getBrand_list() != null && (devicesEntity.getBrand_list().size() > 0))
            for (BrandBean brandBean : devicesEntity.getBrand_list()) {
                if (brandBean.getVal() != null && brandBean.getVal().size() > 0) {
                    for (DeviceBean deviceBean : brandBean.getVal()) {
                        if (devName.equals(deviceBean.getDevice_name())) {
                            return true;
                        }
                    }
                }
            }
        return false;
    }

    // ============= 场景相关 ======


    /**
     * 获取场景列表
     */
    public void getScenariosFromServer(String userid, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + userid);
        final String finalUserid = userid;
        new GetScenariosFromServerModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == RETROFIT_SUCCESS) {
                    ResultGetScenariosEntity brandEntity = (ResultGetScenariosEntity) entity;
                    if (brandEntity != null) {
                        ScenariosListEntity scenariosListEntity = getScenariosListEntity(finalUserid, brandEntity);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(scenariosListEntity, errorCode, error);
                    } else {
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, errorCode, error);
                    }
                } else {
                    if (requestCallback != null)
                        requestCallback.responsedCallback(null, errorCode, error);
                }
            }
        }).execute(userid);
    }

    /**
     * 根据网络请求回来的数据，整理成我们需要的数据
     */
    public ScenariosListEntity getScenariosListEntity(String userid, ResultGetScenariosEntity scenariosEntity) {
        ScenariosListEntity devicesEntity = new ScenariosListEntity();
        devicesEntity.setUserId(userid);
        if (scenariosEntity != null && scenariosEntity.getData() != null && scenariosEntity.getData().size() > 0 && scenariosEntity.getData().get(0).getVal() != null) {
            devicesEntity.setScenarioList(scenariosEntity.getData().get(0).getVal());
        }
        return devicesEntity;
    }

    /**
     * 自定义场景
     */
    public void scenarioGetDevicesModel(String userid, String id, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + userid);
        new GetScenariosDevicesModel(requestCallback).execute(userid, id);
    }

    /**
     * 获取场景下 品牌设备控制参数属性
     */
    public void getDevicesConfigure(final IRequestCallback requestCallback) {
        new GetDevicesConfigureModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    ResultDevicesConfigureEntity configureEntity = (ResultDevicesConfigureEntity) entity;
                    try {
                        HashMap<String, String> dictionariesMap = new Gson().fromJson(String.valueOf(configureEntity.getData().getDictionaries()), new TypeToken<HashMap<String, String>>() {
                        }.getType());
                        configureEntity.getData().setDictionariesMap(dictionariesMap);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(configureEntity, errorCode, error);
                    } catch (IllegalFormatException e) {
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, errorCode, error);
                    }
                } else {
                    if (requestCallback != null)
                        requestCallback.responsedCallback(null, errorCode, error);
                }
            }
        }).execute();

    }

    /**
     * 自定义场景
     */
    public void scenarioCreateModel(ScenarioCreatePOSTEntity tokenEntity, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            tokenEntity.getData().getAttributes().setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + tokenEntity.getData().getAttributes().getUserid());
        new PostScenarioCreateModel(requestCallback).execute(tokenEntity);
    }

    /**
     * 删除场景
     */
    public void scenarioDeleteModel(ScenarioDeletePOSTEntity tokenEntity, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            tokenEntity.getData().getAttributes().setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + tokenEntity.getData().getAttributes().getUserid());
        new PostScenarioDeleteModel(requestCallback).execute(tokenEntity);
    }

    /**
     * 修改场景
     */
    public void scenarioEditModel(ScenarioEditPOSTEntity tokenEntity, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            tokenEntity.getData().getAttributes().setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + tokenEntity.getData().getAttributes().getUserid());
        new PostScenarioEditModel(requestCallback).execute(tokenEntity);
    }

    /**
     * 修改场景名称和图标
     */
    public void PostScenarioEditNameImageModel(ScenarioEditNameImagePOSTEntity tokenEntity, final IRequestCallback requestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            tokenEntity.getData().getAttributes().setUserid(LingManager.getInstance().getTestUserId());
        }
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + tokenEntity.getData().getAttributes().getUserid());
        new PostScenarioEditNameImageModel(requestCallback).execute(tokenEntity);
    }

    /**
     * 判断在已有的场景列表中，是否有重名
     */
    public boolean hasSameName(String devName, ScenariosListEntity scenariosEntity) {
        if (scenariosEntity != null && scenariosEntity.getScenarioList() != null && (scenariosEntity.getScenarioList().size() > 0))
            for (DeviceBean brandBean : scenariosEntity.getScenarioList()) {
                if (devName.equals(brandBean.getDevice_name())) {
                    return true;
                }
            }
        return false;
    }

    // ============ 暂时未用到的方法

    /**
     * 更改幻腾设备名称
     */
    public void updatePhantomDevName(String accessToken, String identId, String newName, final IRequestCallback requestCallback) {
        new UpdatePhantomNameModel(requestCallback).updateName(accessToken, identId, newName);
    }

    /**
     * 更改海尔设备名称
     */
    public void updateHaierDevName(String identId, String newName, final IRequestCallback requestCallback) {
        new HEModel(LingManager.getInstance().getAppContext()).updateDeviceNickName(identId, newName, requestCallback);
    }

    /**
     * 获取BroadLink网络请求时的accessKey.
     */
    public void queryBLKey(IRequestCallback requestCallback) {
        new UpdataBLGetAccessKeyModel(requestCallback).queryKey();
    }

}