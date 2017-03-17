package com.ling.jibonetposa.tools;


import com.ling.jibonetposa.entities.NLUResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface INLUGetRequest {

    @GET("/nlu")
    Call<NLUResult> getCallBack(@QueryMap Map<String,String> params);

}