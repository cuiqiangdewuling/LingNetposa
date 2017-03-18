package com.ling.jibonetposa;

import com.ling.jibonetposa.constants.Constans;
import com.ling.jibonetposa.entities.NLUResult;
import com.ling.jibonetposa.tools.INLUGetRequest;
import com.ling.jibonetposa.tools.IRetrofitHttp;

import java.util.Map;

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
     * @param params
     */
    public void NLURetrofitGet(Map<String,String> params){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.NLUBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INLUGetRequest netRequest= retrofit.create(INLUGetRequest.class);
        Call<NLUResult> callGet = netRequest.getCallBack(params);
        callGet.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                mIRetrofitHttp.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {
                mIRetrofitHttp.onFailure(call,throwable);
            }
        });
    }

    /**
     * nlu的post网络请求
     * @param params
     */
    public void NLURetrofitPost(Map<String,String> params){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.NLUBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        INLUGetRequest netRequest= retrofit.create(INLUGetRequest.class);

        Call<NLUResult> call = netRequest.getCallBack(params);

        call.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                mIRetrofitHttp.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {
                mIRetrofitHttp.onFailure(call,throwable);
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
