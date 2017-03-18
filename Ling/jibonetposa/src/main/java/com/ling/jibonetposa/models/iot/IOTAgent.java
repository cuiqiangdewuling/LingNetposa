package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.entities.TokenEntity;
import com.ling.jibonetposa.tools.IRequestCallback;

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
        new IOTSavePhantomTokenModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    requestCallback.responsedCallback(entity, BaseRequestModel.RETROFIT_SUCCESS, null);
                } else {
                    requestCallback.responsedCallback(entity, errorCode, error);
                }
            }

        }).savePhantomToken(userId, tokenEntity);
    }

    /**
     * 取消幻腾授权
     */
    public void cancelPhantomAuthorized(final AuthorizedEntity authorizedEntity, final IRequestCallback requestCallback) {
        new CancelAuthorizedModel(requestCallback).cancelAuthorized(authorizedEntity.getUserId());
    }

}