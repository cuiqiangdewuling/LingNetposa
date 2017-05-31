package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.ResultGetDevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetDevices;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class GetDevicesFromServerModel extends BaseRequestModel<ResultGetDevicesEntity> {
    
    public GetDevicesFromServerModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getDevices(String userId, int flushType) {
        mParams.put("userid", userId);
        mParams.put("flush", flushType);
        IIOTGetDevices iiotGetToken = retrofit().create(IIOTGetDevices.class);
        Call<ResultGetDevicesEntity> call = iiotGetToken.execute(organizeParams());
        execute(call);
    }
}
