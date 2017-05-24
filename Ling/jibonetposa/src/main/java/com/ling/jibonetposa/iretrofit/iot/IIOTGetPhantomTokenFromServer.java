package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.ResultGetTokenEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_TOKEN_GET;

/**
 * Created by mhz小志 on 2017/4/13.
 */

public interface IIOTGetPhantomTokenFromServer {

    @GET(API_PATH_JIBO_TOKEN_GET)
    Call<ResultGetTokenEntity> execute(@QueryMap Map<String, Object> params);
}
