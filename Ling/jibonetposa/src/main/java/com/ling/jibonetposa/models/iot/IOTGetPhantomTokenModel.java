package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.TokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetToken;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstan.API_PATH_PHANTON;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_APP_ID;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_APP_SECRET;
import static com.ling.jibonetposa.constants.IOTApiConstan.PHANTON_REDIRECT_URI;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTGetPhantomTokenModel extends BaseRequestModel<TokenEntity> {

    public IOTGetPhantomTokenModel(IRequestCallback requestCallback) {
            super(requestCallback);
            this.mApiPath = API_PATH_PHANTON;
        }

    public void getPhantomToken(String authorizeCode) {
        mParams.put("client_id", PHANTON_APP_ID);
        mParams.put("redirect_uri", PHANTON_REDIRECT_URI);
        mParams.put("client_secret", PHANTON_APP_SECRET);
        mParams.put("grant_type", "authorization_code");
        mParams.put("code", authorizeCode);

        IIOTGetToken iiotGetToken = retrofit().create(IIOTGetToken.class);
        Call<TokenEntity> token = iiotGetToken.getToken(organizeParams());
        execute(token);
    }
}
