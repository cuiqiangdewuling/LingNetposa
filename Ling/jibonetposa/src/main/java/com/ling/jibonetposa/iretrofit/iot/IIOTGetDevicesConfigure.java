package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.scenario.ResultDevicesConfigureEntity;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_DEVICES_CONFIGURE;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetDevicesConfigure {

    @GET(API_PATH_JIBO_DEVICES_CONFIGURE)
    Call<ResultDevicesConfigureEntity> execute();
}
