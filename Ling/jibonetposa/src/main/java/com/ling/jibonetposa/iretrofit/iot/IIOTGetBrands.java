package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultGetBrandEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_BRANDS;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetBrands {

    @GET(API_PATH_JIBO_BRANDS)
    Call<ResultGetBrandEntity> execute(@QueryMap Map<String, Object> params);
}
