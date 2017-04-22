package com.ling.jibonetposa.models.iot.haier;

import android.content.Context;

import com.haier.uhome.account.api.interfaces.IAccountListener;
import com.haier.uhome.account.api.uAccount;
import com.haier.uhome.account.model.RespCommonModel;
import com.haier.uhome.account.model.UacDevice;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.RetrofitException;
import com.ling.jibonetposa.entities.HaierAccountEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

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
                LingManager.getInstance().getLingLog().LOGD("doHELogin data:" + data);
                String accountToken = uAccount.getSingleInstance().getAccessToken();
                HaierAccountEntity haierAccount = new HaierAccountEntity();
                haierAccount.setSession(accountToken);
                haierAccount.setUserName(username);
                haierAccount.setPassword(password);
                if (requestCallback != null)
                    requestCallback.responsedCallback(haierAccount, RETROFIT_SUCCESS, null);
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
    }

    /**
     * 获取设备信息
     * Get account's all of smart devcies info
     */
    public void getDevicesOfAccount(final HEListener heListener) {
        uAccount.getSingleInstance().getDeviceList(mContext, null, null, new IAccountListener<UacDevice[]>() {
                    @Override
                    public void onResponseSuccess(UacDevice[] uacDevices) {
                        if (heListener != null) heListener.onResponseSuccess(uacDevices);
                    }

                    @Override
                    public void onResponseFailed(RespCommonModel respCommonModel) {
                        if (heListener != null) heListener.onResponseFailed(respCommonModel);
                    }

                    @Override
                    public void onHttpError(RespCommonModel respCommonModel) {
                        if (heListener != null) heListener.onHttpError(respCommonModel);
                    }
                }
        );
    }


    public interface HEListener {
        void onResponseSuccess(UacDevice[] devices);

        void onResponseFailed(RespCommonModel commonModel);

        void onHttpError(RespCommonModel commonModel);
    }

}
