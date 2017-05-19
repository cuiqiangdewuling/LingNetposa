package com.ling.jibonetposa.iretrofit.location;

import com.ling.jibonetposa.entities.ResultLocationEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO_LOCATION;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface ISaveLocationToServer {

    @Headers({"Content-type:application/json"})
    @POST(API_PATH_JIBO_LOCATION)
    Call<ResultLocationEntity> saveLocation(@Body RequestBody route);
}
