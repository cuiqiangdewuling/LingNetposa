package com.ling.jibonetposa.iretrofit.location;

import com.ling.jibonetposa.entities.locaiton.ResultLocationEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_LOCATION;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IGetLocationFromServer {

    @GET(API_PATH_JIBO_LOCATION)
    Call<ResultLocationEntity> execute(@QueryMap Map<String, Object> params);
}
