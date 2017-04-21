package com.ling.jibo.test;

import com.ling.jibo.test.test.TNLUPostJson;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.NLUConstant;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import retrofit2.Call;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class TNLUModel extends BaseRequestModel<TNLUEntity> {

    public TNLUModel(IRequestCallback requestCallback) {
        super(requestCallback);
        mApiPath = NLUConstant.NLUBaseUrl;
    }

    public void executeResult() {
        mParams.put("userid", "xxxxx");
        mParams.put("words", "你好");
        mParams.put("score", "0.99");
        TNLUPostJson tnluPostRequest = retrofit().create(TNLUPostJson.class);
        Call<TNLUEntity> token = tnluPostRequest.testJson(organizeJsonParams());
        execute(token);
    }
}
