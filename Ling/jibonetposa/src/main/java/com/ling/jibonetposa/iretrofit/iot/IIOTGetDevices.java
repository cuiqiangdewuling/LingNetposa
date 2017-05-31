package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultGetDevicesEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_DEVICES;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetDevices {

    @GET(API_PATH_JIBO_DEVICES)
    Call<ResultGetDevicesEntity> execute(@QueryMap Map<String, Object> params);
}
