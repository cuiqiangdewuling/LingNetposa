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

    @FormUrlEncoded
    @Headers({"Accept:application/json"})
    @PUT
    Call<Object> execute(@Url String url, @Header("Authorization") String accessToken, @Field("name") String name);
}
