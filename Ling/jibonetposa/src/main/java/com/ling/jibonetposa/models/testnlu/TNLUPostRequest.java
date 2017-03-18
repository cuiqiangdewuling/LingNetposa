package com.ling.jibonetposa.models.testnlu;


import com.ling.jibonetposa.entities.TNLUEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface TNLUPostRequest {

    @FormUrlEncoded
    @POST
    Call<TNLUEntity> postCallBack(@Url String url, @FieldMap Map<String, Object> params);
}
