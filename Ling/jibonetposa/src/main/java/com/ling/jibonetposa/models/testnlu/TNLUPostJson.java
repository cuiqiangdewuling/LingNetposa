package com.ling.jibonetposa.models.testnlu;

import com.ling.jibonetposa.entities.TNLUEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface TNLUPostJson {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST
    Call<TNLUEntity> testJson(@Url String url, @Body RequestBody route);
}
