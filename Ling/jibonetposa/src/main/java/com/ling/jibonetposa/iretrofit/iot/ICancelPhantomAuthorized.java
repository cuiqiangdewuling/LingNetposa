package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultCancelAuthEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_TOKEN_DELETE;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface ICancelPhantomAuthorized {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(API_PATH_JIBO_TOKEN_DELETE)
    Call<ResultCancelAuthEntity> execute(@Body RequestBody route);
}
