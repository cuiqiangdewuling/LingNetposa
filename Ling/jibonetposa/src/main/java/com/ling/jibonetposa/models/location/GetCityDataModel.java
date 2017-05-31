package com.ling.jibonetposa.models.location;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.locaiton.ResultCityDataEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.location.IGetCityDataFromServer;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class GetCityDataModel extends BaseRequestModel<ResultCityDataEntity> {

    public GetCityDataModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void getCityData() {
        IGetCityDataFromServer iiotGetToken = retrofit().create(IGetCityDataFromServer.class);
        Call<ResultCityDataEntity> call = iiotGetToken.execute(organizeParams());
        execute(call);
    }
}
