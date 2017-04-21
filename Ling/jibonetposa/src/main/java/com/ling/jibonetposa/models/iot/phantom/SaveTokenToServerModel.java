package com.ling.jibonetposa.models.iot.phantom;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.LingResultEntity;
import com.ling.jibonetposa.entities.SaveTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTSavePhantomTokenToServer;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class SaveTokenToServerModel extends BaseRequestModel<LingResultEntity> {

    public SaveTokenToServerModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void saveToken(String userId, SaveTokenEntity tokenEntity) {

        mParams.put("userid", userId);
        mParams.put("access_token", tokenEntity.getAccess_token());
        mParams.put("refresh_token", tokenEntity.getRefresh_token());
        mParams.put("token_type", tokenEntity.getToken_type());
        mParams.put("expires_in", tokenEntity.getExpires_in());
        mParams.put("created_at", tokenEntity.getCreated_at());

        IIOTSavePhantomTokenToServer iiotGetToken = retrofit().create(IIOTSavePhantomTokenToServer.class);
        Call<LingResultEntity> token = iiotGetToken.saveToken(organizeJsonParams());
        execute(token);
    }
}
