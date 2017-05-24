package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.ResultCancelAuthEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.ICancelPhantomAuthorized;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class CancelAuthorizedModel extends BaseRequestModel<ResultCancelAuthEntity> {

    public CancelAuthorizedModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void cancelAuthorized(String userId, String type) {
        mParams.put("userid", userId);
        mParams.put("type", type);
        ICancelPhantomAuthorized iiotGetToken = retrofit().create(ICancelPhantomAuthorized.class);
        Call<ResultCancelAuthEntity> call = iiotGetToken.execute(organizeJsonParams());
        execute(call);
    }
}
