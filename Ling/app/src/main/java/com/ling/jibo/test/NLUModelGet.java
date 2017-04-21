package com.ling.jibo.test;

import com.ling.jibo.test.test.INLUGetRequest;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.NLUConstant;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cuiqiang on 2017/3/18.
 */

public class NLUModelGet extends BaseRequestModel<NLUEntity> {

    public NLUModelGet(IRequestCallback requestCallback){
        super(requestCallback);
        mApiPath = NLUConstant.NLUBaseUrl;
    }

    public void executedNetRequest(String text){
        Map<String,String> params = new HashMap<>();
        params.put("userid","xxxxx");
        params.put("words",text);
        params.put("score","0.99");
        INLUGetRequest netRequest = retrofit().create(INLUGetRequest.class);
        Call<NLUEntity> call = netRequest.getCallBack(params);
        execute(call);
    }

}
