package com.ling.jibonetposa.iretrofit.userloop;

import com.ling.jibonetposa.entities.userloop.ResultuserLoopEntivity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO_LOOP;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface ISaveLoopsPOST {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(API_PATH_JIBO_LOOP)
    Call<ResultuserLoopEntivity> execute(@Body RequestBody route);
}
