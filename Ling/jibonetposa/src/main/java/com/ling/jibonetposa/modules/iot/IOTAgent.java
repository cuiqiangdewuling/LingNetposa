package com.ling.jibonetposa.modules.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.entities.TokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.iot.CancelAuthorizedModel;
import com.ling.jibonetposa.models.iot.IOTGetPhantomTokenModel;
import com.ling.jibonetposa.models.iot.IOTSavePhantomTokenModel;
import com.ling.jibonetposa.utils.NetWorkUtil;

import java.util.HashMap;
import java.util.Map;

import static com.ling.jibonetposa.constants.IOTApiConstan.API_PATH_PHANTON_AUTHORIZE;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_REDIRECT_URI;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_SCOPE;

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
    public void getPhantomAuthorized(final AuthorizedEntity authorizedEntity, final IRequestCallback requestCallback) {

        new IOTGetPhantomTokenModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    TokenEntity tokenEntity = (TokenEntity) entity;
                    doSaveToken(authorizedEntity.getUserId(), tokenEntity, requestCallback);
                } else {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }
        }).getPhantomToken(authorizedEntity.getAuthorizedCode());
    }

    /**
     * 将Token保存
     */
    private void doSaveToken(String userId, TokenEntity tokenEntity, final IRequestCallback requestCallback) {
        new IOTSavePhantomTokenModel(requestCallback).savePhantomToken(userId, tokenEntity);
    }

    /**
     * 取消幻腾授权
     */
    public void cancelPhantomAuthorized(final AuthorizedEntity authorizedEntity, final IRequestCallback requestCallback) {
        new CancelAuthorizedModel(requestCallback).cancelAuthorized(authorizedEntity.getUserId());
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
        return  API_PATH_PHANTON_AUTHORIZE + NetWorkUtil.organizeParams(mParams);
    }
}