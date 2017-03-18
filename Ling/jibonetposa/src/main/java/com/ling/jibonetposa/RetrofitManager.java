package com.ling.jibonetposa;

import com.ling.jibonetposa.tools.IRetrofitHttp;

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


    public IRetrofitHttp getRetrofitHttp() {
        return mIRetrofitHttp;
    }

    public void setRetrofitHttp(IRetrofitHttp retrofitHttp) {
        this.mIRetrofitHttp = retrofitHttp;
    }



}
