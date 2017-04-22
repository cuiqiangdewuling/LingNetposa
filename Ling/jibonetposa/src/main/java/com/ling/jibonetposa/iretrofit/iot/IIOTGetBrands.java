package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.ResultGetBrandEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_BRANDS;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetBrands {

    @GET(API_PATH_JIBO_BRANDS)
    Call<ResultGetBrandEntity> getBrands(@QueryMap Map<String, Object> params);
}
