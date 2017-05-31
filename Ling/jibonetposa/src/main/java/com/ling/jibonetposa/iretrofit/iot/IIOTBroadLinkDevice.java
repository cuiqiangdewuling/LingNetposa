package com.ling.jibonetposa.iretrofit.iot;

import com.ling.jibonetposa.entities.iot.ResultBLUpdataName;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_BROADLINK_VERIFY;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IIOTBroadLinkDevice {

    @POST(API_PATH_BROADLINK_VERIFY)
    Call<ResultBLUpdataName> execute(@HeaderMap Map<String, String> headers, @Body RequestBody route);
}
