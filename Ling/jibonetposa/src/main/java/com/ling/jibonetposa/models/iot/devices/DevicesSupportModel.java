package com.ling.jibonetposa.models.iot.devices;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.ResultDevicesSupportEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetDevicesSupport;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class DevicesSupportModel extends BaseRequestModel<ResultDevicesSupportEntity> {

    public DevicesSupportModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getSupport(String brandid) {
        mParams.put("id", brandid);
        IIOTGetDevicesSupport iiotGetToken = retrofit().create(IIOTGetDevicesSupport.class);
        Call<ResultDevicesSupportEntity> call = iiotGetToken.execute(organizeParams());
        execute(call);
    }
}
