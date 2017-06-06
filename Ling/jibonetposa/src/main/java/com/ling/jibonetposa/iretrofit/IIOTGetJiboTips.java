package com.ling.jibonetposa.iretrofit;

import com.ling.jibonetposa.entities.ResultGetTipsEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_INFORMATION;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetJiboTips {

    @GET(API_PATH_JIBO_INFORMATION)
    Call<ResultGetTipsEntity> execute(@QueryMap Map<String, Object> params);
}
