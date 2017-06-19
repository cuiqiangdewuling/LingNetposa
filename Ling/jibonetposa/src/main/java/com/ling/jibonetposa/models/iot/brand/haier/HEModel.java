package com.ling.jibonetposa.models.iot.brand.haier;

import android.content.Context;

import com.haier.uhome.account.api.interfaces.IAccountListener;
import com.haier.uhome.account.api.uAccount;
import com.haier.uhome.account.model.RespCommonModel;
import com.haier.uhome.account.model.UacDevice;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.RetrofitException;
import com.ling.jibonetposa.entities.iot.HaierAccountEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import static com.ling.jibonetposa.base.BaseRequestModel.ERROR_PASS_MISTAKE;
import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_ERROR;
import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class HEModel {

    private Context mContext;

    public HEModel(Context context) {
        this.mContext = context;
    }

    public void doHELogin(final String username, final String password, final IRequestCallback requestCallback) {
        uAccount.getSingleInstance().login(mContext, username, password, new IAccountListener<String>() {
            @Override
            public void onResponseSuccess(String data) {
                if (requestCallback != null) {
                    LingManager.getInstance().getLingLog().LOGD("doHELogin data:" + data);
                    String accountToken = uAccount.getSingleInstance().getAccessToken();
                    HaierAccountEntity haierAccount = new HaierAccountEntity();
                    haierAccount.setSession(accountToken);
                    haierAccount.setUserName(username);
                    haierAccount.setPassword(password);
                    requestCallback.responsedCallback(haierAccount, RETROFIT_SUCCESS, null);
                }
            }

            @Override
            public void onResponseFailed(RespCommonModel respCommonModel) {
                LingManager.getInstance().getLingLog().LOGD("onResponseFailed:" + respCommonModel);
                if (requestCallback != null) {
                    if (respCommonModel.getRetCode().equals("B22109-22820")) {
                        requestCallback.responsedCallback(null, ERROR_PASS_MISTAKE, new RetrofitException(respCommonModel.getRetInfo()));
                    } else {
                        requestCallback.responsedCallback(null, RETROFIT_ERROR, new RetrofitException(respCommonModel.getRetInfo()));
                    }
                }
            }

            @Override
            public void onHttpError(RespCommonModel respCommonModel) {
                LingManager.getInstance().getLingLog().LOGD("onHttpError:" + respCommonModel);
                if (requestCallback != null)
                    requestCallback.responsedCallback(null, RETROFIT_ERROR, new RetrofitException(respCommonModel.getRetInfo()));
            }
        });
    }

    public void updateDeviceNickName(final String deviceId, final String deviceNewNickName, final IRequestCallback requestCallback) {
        uAccount.getSingleInstance().updateNickname(
                mContext, deviceId, deviceNewNickName, new IAccountListener<RespCommonModel>() {
                    @Override
                    public void onResponseSuccess(RespCommonModel commonModel) {
                        LingManager.getInstance().getLingLog().LOGD("onResponseFailed:" + commonModel);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, RETROFIT_SUCCESS, null);
                    }

                    @Override
                    public void onResponseFailed(RespCommonModel respCommonModel) {
                        LingManager.getInstance().getLingLog().LOGD("onResponseFailed:" + respCommonModel);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, RETROFIT_ERROR, new RetrofitException(respCommonModel.getRetInfo()));
                    }

                    @Override
                    public void onHttpError(RespCommonModel respCommonModel) {
                        LingManager.getInstance().getLingLog().LOGD("onHttpError:" + respCommonModel);
                        if (requestCallback != null)
                            requestCallback.responsedCallback(null, RETROFIT_ERROR, new RetrofitException(respCommonModel.getRetInfo()));
                    }
                });
//                new UAccountCommonLogicAdapter(mContext) {
//                    @Override
//                    public void actionLogic(RespCommonModel data) {
//                        System.out.println("uAccount update smartdevice nickname success!");
//                        deviceNameModifyDialog.dismiss();
//                        getDevicesOfAccount();
//
//                    }
//                });
    }

    public interface HEListener {
        void onResponseSuccess(UacDevice[] devices);

        void onResponseFailed(RespCommonModel commonModel);

        void onHttpError(RespCommonModel commonModel);
    }

}
