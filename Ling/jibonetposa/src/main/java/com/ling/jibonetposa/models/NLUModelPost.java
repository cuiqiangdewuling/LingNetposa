package com.ling.jibonetposa.models;

import com.ling.jibonetposa.RetrofitManager;
import com.ling.jibonetposa.constants.Constans;
import com.ling.jibonetposa.entities.NLUResult;
import com.ling.jibonetposa.tools.INLUPostRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by cuiqiang on 2017/3/18.
 */

public class NLUModelPost {

    private String mApiPath;
    private RetrofitManager mRetrofitManager;

    public NLUModelPost(RetrofitManager retrofitManager){
        mRetrofitManager = retrofitManager;
        mApiPath = Constans.NLUBaseUrl;
    }

    public Call<NLUResult> executedNetRequest(String text){
        Map<String,String> params = new HashMap<>();
        params.put("userid","xxxxx");
        params.put("words",text);
        params.put("score","0.99");
        Retrofit retrofit = mRetrofitManager.retrofit(mApiPath);
        INLUPostRequest netRequest= retrofit.create(INLUPostRequest.class);
        Call<NLUResult> call = netRequest.postCallBack(params);
        return call;
    }

}
