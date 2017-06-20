package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.scenario.ResultScenarioSaveAllEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_SCENARIOS_CUSTOM;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IScenarioEditNameImagePOST {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(API_PATH_JIBO_SCENARIOS_CUSTOM)
    Call<ResultScenarioSaveAllEntity> execute(@Body RequestBody route);
}
