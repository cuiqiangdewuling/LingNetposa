package com.ling.jibonetposa.models.testnlu;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface TNLUPostRequest {

    @FormUrlEncoded
    @POST("/nlu")
    Call<TNLUEntity> postCallBack(@FieldMap Map<String, Object> params);
}
