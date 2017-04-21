package com.ling.jibonetposa.models.iot.phantom;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.SaveTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetPhantomTokenFromPhantom;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_PHANTON;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_APP_SECRET;
import static com.ling.jibonetposa.constants.IOTApiConstant.PHANTON_REDIRECT_URI;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class GetPhantomTokenModel extends BaseRequestModel<SaveTokenEntity> {

    public GetPhantomTokenModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_PHANTON;
    }

    public void getPhantomToken(String authorizeCode) {
        mParams.put("client_id", PHANTON_APP_ID);
        mParams.put("redirect_uri", PHANTON_REDIRECT_URI);
        mParams.put("client_secret", PHANTON_APP_SECRET);
        mParams.put("grant_type", "authorization_code");
        mParams.put("code", authorizeCode);

        IIOTGetPhantomTokenFromPhantom iiotGetTokenFromPhantom = retrofit().create(IIOTGetPhantomTokenFromPhantom.class);
        Call<SaveTokenEntity> token = iiotGetTokenFromPhantom.getToken(organizeParams());
        execute(token);
    }
}
