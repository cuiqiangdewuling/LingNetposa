package com.ling.jibonetposa.models;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.Constans;
import com.ling.jibonetposa.entities.TNLUEntity;
import com.ling.jibonetposa.tools.INLUGetRequest;
import com.ling.jibonetposa.tools.IRequestCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cuiqiang on 2017/3/18.
 */

public class NLUModelGet extends BaseRequestModel<TNLUEntity> {

    public NLUModelGet(IRequestCallback requestCallback){
        super(requestCallback);
        mApiPath = Constans.NLUBaseUrl;
    }

    public void executedNetRequest(String text){
        Map<String,String> params = new HashMap<>();
        params.put("userid","xxxxx");
        params.put("words",text);
        params.put("score","0.99");
        INLUGetRequest netRequest = retrofit().create(INLUGetRequest.class);
        Call<TNLUEntity> call = netRequest.getCallBack(params);
        execute(call);
    }

}
