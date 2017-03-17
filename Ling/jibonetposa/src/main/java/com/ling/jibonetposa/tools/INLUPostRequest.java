package com.ling.jibonetposa.tools;


import com.ling.jibonetposa.entities.NLUResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface INLUPostRequest {

    @FormUrlEncoded
    @POST("/nlu")
    Call<NLUResult> postCallBack(@FieldMap Map<String, String> params);
}
