package com.jibo.test;

import android.content.Context;

import com.jibo.test.test.INLUGetRequest;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.BaseHttpConstant;
import com.ling.jibonetposa.constants.NLUConstant;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cuiqiang on 2017/3/22.
 */

public class NLUModelCacheGet extends BaseRequestModel<NLUEntity> {

    public NLUModelCacheGet(IRequestCallback requestCallback){
        super(requestCallback);
        mApiPath = NLUConstant.NLUBaseUrl;
    }

    public void executedNetRequest(String text, Context context) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", "xxxxx");
        params.put("words", text);
        params.put("score", "0.99");
        INLUGetRequest netRequest = retrofitCache(context, BaseHttpConstant.INTERNAL, "me").create(INLUGetRequest.class);
        Call<NLUEntity> call = netRequest.getCallBack(params);
        execute(call);
    }
}
