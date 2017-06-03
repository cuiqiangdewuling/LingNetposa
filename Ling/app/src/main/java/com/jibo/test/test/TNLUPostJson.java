package com.jibo.test.test;


import com.jibo.test.TNLUEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface TNLUPostJson {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("/nlu")
    Call<TNLUEntity> testJson(@Body RequestBody route);
}
