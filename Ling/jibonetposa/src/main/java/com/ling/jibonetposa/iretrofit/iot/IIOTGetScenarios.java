package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultGetScenariosEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_SCENARIOS;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetScenarios {

    @GET(API_PATH_JIBO_SCENARIOS)
    Call<ResultGetScenariosEntity> execute(@QueryMap Map<String, Object> params);
}
