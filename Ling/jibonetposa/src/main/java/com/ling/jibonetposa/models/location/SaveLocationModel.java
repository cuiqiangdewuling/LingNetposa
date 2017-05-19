package com.ling.jibonetposa.models.location;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.ResultLocationEntity;
import com.ling.jibonetposa.entities.SaveLocationEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.location.ISaveLocationToServer;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class SaveLocationModel extends BaseRequestModel<ResultLocationEntity> {

    public SaveLocationModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void saveLocation(SaveLocationEntity resultLocationEntity) {
        ISaveLocationToServer iiotGetToken = retrofit().create(ISaveLocationToServer.class);
        Call<ResultLocationEntity> call = iiotGetToken.saveLocation(organizeJsonParams(resultLocationEntity));
        execute(call);
    }
}
