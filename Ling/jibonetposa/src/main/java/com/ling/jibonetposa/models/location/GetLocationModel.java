package com.ling.jibonetposa.models.location;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.locaiton.ResultLocationEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.location.IGetLocationFromServer;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class GetLocationModel extends BaseRequestModel<ResultLocationEntity> {

    public GetLocationModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getLocation(String userId) {
        mParams.put("userid", userId);
        IGetLocationFromServer iiotGetToken = retrofit().create(IGetLocationFromServer.class);
        Call<ResultLocationEntity> call = iiotGetToken.execute(organizeParams());
        execute(call);
    }
}
