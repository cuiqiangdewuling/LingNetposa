package com.ling.jibonetposa.models;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.ResultGetTipsEntity;
import com.ling.jibonetposa.iretrofit.IIOTGetJiboTips;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/6/5.
 */

public class GetTipsModel extends BaseRequestModel<ResultGetTipsEntity> {

    public GetTipsModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getIOTTip(int classId) {

        mParams.put("class_id", classId);

        IIOTGetJiboTips iiotGetToken = retrofit().create(IIOTGetJiboTips.class);
        Call<ResultGetTipsEntity> token = iiotGetToken.execute(organizeParams());
        execute(token);
    }
}
