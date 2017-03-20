package com.ling.jibonetposa.base;

import com.google.gson.Gson;
import com.ling.jibonetposa.modules.IRequestCallback;

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

    protected IRequestCallback mRequestCallback;
    protected Map<String, Object> mParams = new HashMap<>();
    protected String mApiPath;

    public BaseRequestModel(IRequestCallback requestCallback) {
        mRequestCallback = requestCallback;
    }

    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mApiPath)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    protected RequestBody organizeJsonParams() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String resultJson = gson.toJson(this.mParams);
        sb.append(resultJson);
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), sb.toString());
    }

    protected Map<String, Object> organizeParams() {
        return mParams;
    }

    protected void execute(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    BaseRequestModel.this.mRequestCallback.responsedCallback(response.body(), 0, null);
                } else {
                    BaseRequestModel.this.mRequestCallback.responsedCallback(null, -1, null);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                BaseRequestModel.this.mRequestCallback.responsedCallback(null, 1, t);
            }
        });
    }
}
