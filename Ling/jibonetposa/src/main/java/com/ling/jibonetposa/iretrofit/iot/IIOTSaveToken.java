package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.LingResultEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_SAVETOKEN;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTSaveToken {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(API_PATH_JIBO_SAVETOKEN)
    Call<LingResultEntity> saveToken(@Body RequestBody route);
}
