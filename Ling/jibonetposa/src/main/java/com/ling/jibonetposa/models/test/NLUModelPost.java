package com.ling.jibonetposa.models.test;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.NLUConstant;
import com.ling.jibonetposa.entities.NLUEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.test.INLUPostRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cuiqiang on 2017/3/18.
 */

public class NLUModelPost extends BaseRequestModel<NLUEntity> {

    public NLUModelPost(IRequestCallback requestCallback){
        super(requestCallback);
        mApiPath = NLUConstant.NLUBaseUrl;
    }

    public void executedNetRequest(String text){
        Map<String,String> params = new HashMap<>();
        params.put("userid","xxxxx");
        params.put("words",text);
        params.put("score","0.99");
        INLUPostRequest netRequest= retrofit().create(INLUPostRequest.class);
        Call<NLUEntity> call= netRequest.postCallBack(params);
        execute(call);
    }
}
