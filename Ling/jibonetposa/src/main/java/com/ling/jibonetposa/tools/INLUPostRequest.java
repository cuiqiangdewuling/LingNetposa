package com.ling.jibonetposa.tools;


import com.ling.jibonetposa.entities.TNLUEntity;

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
    //当@FormUrlEncoded存在于方法上时，发送表单编码的数据。每个键值对都注释有@Field包含名称和提供值的对象。
    @POST("/nlu")
    Call<TNLUEntity> postCallBack(@FieldMap Map<String, String> params);
}
