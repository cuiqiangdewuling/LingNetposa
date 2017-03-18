package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.LingResultEntity;
import com.ling.jibonetposa.tools.ICancelAuthorized;
import com.ling.jibonetposa.tools.IRequestCallback;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstan.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class CancelAuthorizedModel extends BaseRequestModel<LingResultEntity> {

    public CancelAuthorizedModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void cancelAuthorized(String userId) {

        mParams.put("userid", userId);
        ICancelAuthorized iiotGetToken = retrofit().create(ICancelAuthorized.class);
        Call<LingResultEntity> call = iiotGetToken.saveToken(organizeJsonParams());
        execute(call);
    }
}
