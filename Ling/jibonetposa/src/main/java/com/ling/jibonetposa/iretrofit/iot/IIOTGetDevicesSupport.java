package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultDevicesSupportEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_DEVICES_SUPPORT;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetDevicesSupport {

    @GET(API_PATH_JIBO_DEVICES_SUPPORT)
    Call<ResultDevicesSupportEntity> execute(@QueryMap Map<String, Object> params);
}
