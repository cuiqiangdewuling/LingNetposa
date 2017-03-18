package com.ling.jibonetposa.base;

import android.util.Log;

import com.google.gson.Gson;
import com.ling.jibonetposa.tools.IRequestCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class BaseRequestModel<T extends BaseEntity> {

    public static final int RETROFIT_SUCCESS = 0;
    public static final int RETROFIT_ERROR = 1;
    public static final int RETROFIT_WRONG = -1;

    protected IRequestCallback mRequestCallback;
    protected Map<String, Object> mParams = new HashMap<>();
    protected String mApiPath;

    public BaseRequestModel(IRequestCallback requestCallback) {
        mRequestCallback = requestCallback;
    }

    /**
     * 获取Retrofit网络请求对象
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mApiPath)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 如果请求是带Json格式的请求，执行此方法将参数转换成Json
     */
    protected RequestBody organizeJsonParams() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String resultJson = gson.toJson(this.mParams);
        sb.append(resultJson);
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), sb.toString());
    }

    /**
     * 如果请求是带普通参数的请求，执行此方法返回mParams，
     * <p>
     * 以后如果有需求的话，可以在这里对Params做数据处理
     */
    protected Map<String, Object> organizeParams() {
        return mParams;
    }

    /**
     * Retrofit进行网络请求，
     *
     * @param call
     */
    protected void execute(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    BaseRequestModel.this.mRequestCallback.responsedCallback(response.body(), RETROFIT_SUCCESS, (Throwable) null);
                } else {
                    Log.d("123123", "response.toString()   " + response.toString());
                    BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_WRONG, new RetrofitException(response.toString()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_ERROR, throwable);
            }
        });
    }
}
