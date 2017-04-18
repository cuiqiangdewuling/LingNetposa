package com.ling.jibonetposa.models.phantom;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.LingResultEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetPhantomTokenFromServer;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTGetTokenFromServerModel extends BaseRequestModel<LingResultEntity> {

    public IOTGetTokenFromServerModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getPhantomToken(String userid) {
        mParams.put("userid", userid);
        IIOTGetPhantomTokenFromServer iiotGetToken = retrofit().create(IIOTGetPhantomTokenFromServer.class);
        Call<LingResultEntity> token = iiotGetToken.getToken(organizeParams());
        execute(token);
    }
}
