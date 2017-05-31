package com.ling.jibonetposa.iretrofit.location;

import com.ling.jibonetposa.entities.locaiton.ResultCityDataEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_CITY;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IGetCityDataFromServer {

    @GET(API_PATH_JIBO_CITY)
    Call<ResultCityDataEntity> execute(@QueryMap Map<String, Object> params);
}
