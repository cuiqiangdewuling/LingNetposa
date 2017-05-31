package com.ling.jibonetposa.models.iot.broadlink;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.BroadLinkTokenEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetBroadLinkTokenFromBroadLink;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.BROADLINK_API_PATH;
import static com.ling.jibonetposa.constants.IOTApiConstant.BROADLINK_CLIENT_ID;
import static com.ling.jibonetposa.constants.IOTApiConstant.BROADLINK_CLIENT_SECRET;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class GetBroadLinkTokenModel extends BaseRequestModel<BroadLinkTokenEntity> {

    public GetBroadLinkTokenModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = BROADLINK_API_PATH;
    }

    public void getBroadLinkToken(String authorizeCode) {
        mParams.put("client_id", BROADLINK_CLIENT_ID);
        mParams.put("client_secret", BROADLINK_CLIENT_SECRET);
        mParams.put("grant_type", "authorization_code");
        mParams.put("code", authorizeCode);

        IIOTGetBroadLinkTokenFromBroadLink iiotGetTokenFromPhantom = retrofit().create(IIOTGetBroadLinkTokenFromBroadLink.class);
        Call<BroadLinkTokenEntity> token = iiotGetTokenFromPhantom.execute(organizeParams());
        execute(token);
    }
}
