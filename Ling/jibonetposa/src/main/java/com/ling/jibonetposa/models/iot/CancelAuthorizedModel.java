package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.ResultGetTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.ICancelPhantomAuthorized;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class CancelAuthorizedModel extends BaseRequestModel<ResultGetTokenEntity> {

    public CancelAuthorizedModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    /**
     * @param type 0：幻腾   1：海尔   2：博联
     */
    public void cancelAuthorized(String userId, int type) {
        mParams.put("userid", userId);
        mParams.put("type", type);
        ICancelPhantomAuthorized iiotGetToken = retrofit().create(ICancelPhantomAuthorized.class);
        Call<ResultGetTokenEntity> call = iiotGetToken.saveToken(organizeJsonParams());
        execute(call);
    }
}
