package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.LingResultEntity;
import com.ling.jibonetposa.entities.TokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTSaveToken;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTSavePhantomTokenModel extends BaseRequestModel<LingResultEntity> {

    public IOTSavePhantomTokenModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void savePhantomToken(String userId, TokenEntity tokenEntity) {

        mParams.put("userid", userId);
        mParams.put("access_token", tokenEntity.getAccess_token());
        mParams.put("refresh_token", tokenEntity.getRefresh_token());
        mParams.put("token_type", tokenEntity.getToken_type());
        mParams.put("expires_in", tokenEntity.getExpires_in());
        mParams.put("created_at", tokenEntity.getCreated_at());

        IIOTSaveToken iiotGetToken = retrofit().create(IIOTSaveToken.class);
        Call<LingResultEntity> token = iiotGetToken.saveToken(organizeJsonParams());
        execute(token);
    }
}
