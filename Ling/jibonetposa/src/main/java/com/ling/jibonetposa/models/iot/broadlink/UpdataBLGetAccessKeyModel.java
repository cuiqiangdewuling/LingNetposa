package com.ling.jibonetposa.models.iot.broadlink;

import com.google.gson.Gson;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.ResultBLUpdataName;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTBroadLinkDevice;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_BROADLINK_DEVICE;
import static com.ling.jibonetposa.constants.IOTApiConstant.BROADLINK_LICENSE;

/**
 * Created by mhz小志 on 2017/5/20.
 */

public class UpdataBLGetAccessKeyModel extends BaseRequestModel<ResultBLUpdataName> {

    public UpdataBLGetAccessKeyModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_BROADLINK_DEVICE;
    }

//    public void updateKey() {
//        mParams.put("act", "update");
//        mParams.put("disaccesskey", "");
//        mParams.put("accesskey", "");
//        mParams.put("license", BROADLINK_LICENSE);
//        IIOTBroadLinkDevice iiotGetToken = retrofit().create(IIOTBroadLinkDevice.class);
//        Call<ResultBLUpdataName> call = iiotGetToken.execute(getHeaders(), organizeJsonParams());
//        execute(call);
//    }

    public void addKey(String accesskey) {
        mParams.put("act", "addKey");
        mParams.put("accesskey", accesskey);
        mParams.put("license", BROADLINK_LICENSE);
        IIOTBroadLinkDevice iiotGetToken = retrofit().create(IIOTBroadLinkDevice.class);
        Call<ResultBLUpdataName> call = iiotGetToken.execute(getHeaders(), organizeJsonParams());
        execute(call);
    }

    public void queryKey() {
        mParams.put("act", "queryBLKey");
        mParams.put("license", BROADLINK_LICENSE);
        IIOTBroadLinkDevice iiotGetToken = retrofit().create(IIOTBroadLinkDevice.class);
        Call<ResultBLUpdataName> call = iiotGetToken.execute(getHeaders(), organizeJsonParams());
        execute(call);
    }

    private Map<String, String> getHeaders() {
        Map<String, String> mHeaders = new HashMap<>();
        long timestamp = System.currentTimeMillis() / 1000;
        String body = new Gson().toJson(this.mParams);
        String signature = BLUtil.getBLSign(timestamp, body);
        mHeaders.put("timestamp", timestamp + "");
        mHeaders.put("signature", signature);
        LingManager.getInstance().getLingLog().LOGD("timestamp:   " + timestamp);
        LingManager.getInstance().getLingLog().LOGD("signature:   " + signature);
        LingManager.getInstance().getLingLog().LOGD("body:   " + body);
        return mHeaders;
    }
}
