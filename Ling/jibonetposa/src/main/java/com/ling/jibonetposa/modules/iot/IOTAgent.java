package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedCodeEntity;
import com.ling.jibonetposa.entities.PhantomAccessTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.iot.IOTCancelPhantomAuthorizedModel;
import com.ling.jibonetposa.models.iot.IOTGetPhantomDevicesFromPhantomModel;
import com.ling.jibonetposa.models.iot.IOTGetPhantomTokenFromServerModel;
import com.ling.jibonetposa.models.iot.IOTGetPhantomTokenModel;
import com.ling.jibonetposa.models.iot.IOTSavePhantomTokenToServerModel;
import com.ling.jibonetposa.utils.NetWorkUtil;

import java.util.HashMap;
import java.util.Map;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_REDIRECT_URI;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_SCOPE;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTAgent {

    /**
     * 获取幻腾授权
     * <p>
     * 根据authorizedCode去获取幻腾Token，然后将Token保存到服务器
     *
     * @param authorizedEntity 获取授权所需要的参数
     */
    public static void getPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
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
    public static void getPhantomTokenFromServer(String userId, final IRequestCallback requestCallback) {
        new IOTGetPhantomTokenFromServerModel(requestCallback).getPhantomToken(userId);
    }

    /**
     * 获取保存的Phantom Token
     */
    public static void getPhantomDevicesFromPhantom(String accessToken, final IRequestCallback requestCallback) {
        new IOTGetPhantomDevicesFromPhantomModel(requestCallback).getPhantomDevices(accessToken);
    }

    /**
     * 将Token保存
     */
    private static void doSavePhantomTokenToServer(String userId, PhantomAccessTokenEntity tokenEntity, final IRequestCallback requestCallback) {
        new IOTSavePhantomTokenToServerModel(requestCallback).savePhantomToken(userId, tokenEntity);
    }

    /**
     * 取消幻腾授权
     */
    public static void cancelPhantomAuthorized(final AuthorizedCodeEntity authorizedEntity, final IRequestCallback requestCallback) {
        new IOTCancelPhantomAuthorizedModel(requestCallback).cancelAuthorized(authorizedEntity.getUserId());
    }

    /**
     * 合成授权页面的请求地址
     */
    public static String getPhantomAuthorizedUrl() {
        Map<String, Object> mParams = new HashMap<String, Object>();
        mParams.put("client_id", PHANTON_APP_ID);
        mParams.put("redirect_uri", PHANTON_REDIRECT_URI);
        mParams.put("response_type", "code");
        mParams.put("scope", PHANTON_SCOPE);//PHANTON_SCOPE
        return API_PATH_PHANTON_AUTHORIZE + NetWorkUtil.organizeParams(mParams);
    }
}