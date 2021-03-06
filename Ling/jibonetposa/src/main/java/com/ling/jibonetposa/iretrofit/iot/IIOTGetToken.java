package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.TokenEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON_TOKEN;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTGetToken {

    @FormUrlEncoded
    @POST(API_PATH_PHANTON_TOKEN)
    Call<TokenEntity> getToken(@FieldMap Map<String, Object> params);
}
