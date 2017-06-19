package com.ling.jibonetposa.models.iot.brand.phantom;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTUpdatePhantomName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_PHANTON_DEV;
import static com.ling.jibonetposa.constants.APIConstant.API_PATH_PHANTON_UPDATE_NAME;

/**
 * Created by mhz小志 on 2017/5/4.
 */

public class UpdatePhantomNameModel extends BaseRequestModel {

    public UpdatePhantomNameModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_PHANTON_DEV;
    }

    public void updateName(String accessToken, String identifier, String newName) {
        IIOTUpdatePhantomName iiotGetToken = retrofit().create(IIOTUpdatePhantomName.class);
        mCall = iiotGetToken.execute(API_PATH_PHANTON_UPDATE_NAME + identifier + ".json", "bearer " + accessToken, newName);
        mCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "executeSuccess " + response.toString());
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "error " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                LingManager.getInstance().getLingLog().LOGD(TAG, "onFailure " + throwable.toString());
            }
        });
    }
}
