package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.scenario.ResultScenarioCustomEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_LOCATION;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IScenarioEditPOST {

    @Headers({"Content-type:application/json"})
    @POST(API_PATH_JIBO_LOCATION)
    Call<ResultScenarioCustomEntity> execute(@Body RequestBody route);
}
