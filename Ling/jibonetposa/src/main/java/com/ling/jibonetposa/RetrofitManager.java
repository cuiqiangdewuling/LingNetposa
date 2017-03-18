package com.ling.jibonetposa;

import com.ling.jibonetposa.entities.NLUResult;
import com.ling.jibonetposa.models.NLUModelGet;
import com.ling.jibonetposa.models.NLUModelPost;
import com.ling.jibonetposa.tools.IRetrofitHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



/**
 * Created by cuiqiang on 2017/3/16.
 */

public class RetrofitManager {

    private IRetrofitHttp mIRetrofitHttp;

    public Retrofit retrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * nlu的get网络请求
     * @param text
     */
    public void NLURetrofitNetGet(String text,final IRetrofitHttp retrofitHttp){
        NLUModelGet nluModelGet = new NLUModelGet(this);
        Call<NLUResult> nluResultCall = nluModelGet.executedNetRequest(text);
        nluResultCall.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                retrofitHttp.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {
                retrofitHttp.onFailure(call,throwable);
            }
        });
    }

    /**
     * nlu的post网络请求
     * @param text
     */
    public void NLURetrofitNetPost(String text,final IRetrofitHttp retrofitHttp){
        NLUModelPost nluModelPost = new NLUModelPost(this);
        Call<NLUResult> nluResultCall = nluModelPost.executedNetRequest(text);
        nluResultCall.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                retrofitHttp.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {
                retrofitHttp.onFailure(call,throwable);
            }
        });
    }

    public IRetrofitHttp getRetrofitHttp() {
        return mIRetrofitHttp;
    }

    public void setRetrofitHttp(IRetrofitHttp retrofitHttp) {
        this.mIRetrofitHttp = retrofitHttp;
    }


}
