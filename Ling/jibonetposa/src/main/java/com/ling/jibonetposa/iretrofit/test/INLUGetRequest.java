package com.ling.jibonetposa.iretrofit.test;


import com.ling.jibonetposa.entities.NLUEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface INLUGetRequest{

     @GET("/nlu")
     Call<NLUEntity> getCallBack(@QueryMap Map<String,String> params);

}
