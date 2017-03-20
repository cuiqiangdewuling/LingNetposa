package com.ling.jibonetposa.models.test;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.constants.NLUConstans;
import com.ling.jibonetposa.entities.TNLUEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.test.TNLUPostJson;

import retrofit2.Call;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class TNLUModel extends BaseRequestModel<TNLUEntity> {

    public TNLUModel(IRequestCallback requestCallback) {
        super(requestCallback);
        mApiPath = NLUConstans.NLUBaseUrl;
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
