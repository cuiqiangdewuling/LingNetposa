package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultHaierLogin;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_HAIER_LOGIN;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IHaierLoginPost {

    @POST(API_PATH_HAIER_LOGIN)
    Call<ResultHaierLogin> execute(@HeaderMap Map<String, String> headers, @Body RequestBody route);
}
