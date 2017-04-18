package com.ling.jibonetposa.models.haier;

import android.content.Context;

import com.haier.uhome.account.api.interfaces.IAccountListener;
import com.haier.uhome.account.api.uAccount;
import com.haier.uhome.account.model.RespCommonModel;
import com.haier.uhome.account.model.UacDevice;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class HEModel {

    private Context mContext;

    public HEModel(Context context) {
        this.mContext = context;
    }

    public void doHELogin(String username, String password, HEListener heListener) {
        //todo 这里应该有数据校验

        uAccount.getSingleInstance().login(mContext, username, password, new IAccountListener<String>() {
            @Override
            public void onResponseSuccess(String data) {
//                LogD("data: " + data);
//                String accountToken = accountHelper.getAccessToken();
//                HaierAccount haierAccount = new HaierAccount();
//                haierAccount.setSession(accountToken);
//                haierAccount.setUserName(username);
//                haierAccount.setPassword(password);
//                HaierAccountUtil.saveHaierAccount(haierAccount);
//                startActivity(new Intent(mContext, UAccountBaseInfoActivity.class));
//                finish();
            }

            @Override
            public void onResponseFailed(RespCommonModel respCommonModel) {
//                LogD("onResponseFailed:" + respCommonModel);
//                Toast.makeText(getApplicationContext(), "" + respCommonModel.getRetInfo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHttpError(RespCommonModel respCommonModel) {
//                LogD("onHttpError:" + respCommonModel);
//                Toast.makeText(getApplicationContext(), "" + respCommonModel.getRetInfo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取设备信息
     * Get account's all of smart devcies info
     */
    private void getDevicesOfAccount(final HEListener heListener) {
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
