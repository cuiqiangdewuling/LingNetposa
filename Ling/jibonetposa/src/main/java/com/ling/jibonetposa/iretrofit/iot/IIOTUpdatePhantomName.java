package com.ling.jibonetposa.iretrofit.iot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Url;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTUpdatePhantomName {

    //    @PUT(API_PATH_PHANTON_UPDATE_NAME)
//    Call<Object> updateName(@Header("Authorization") String accessToken, @Url String identifier, @Field("name") String name);
    @FormUrlEncoded
    @Headers({"Accept:application/json"})
    @PUT
    Call<Object> updateName(@Url String url, @Header("Authorization") String accessToken, @Field("name") String name);
}
