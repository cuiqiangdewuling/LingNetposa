package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.PutUpdateDevNameEntity;
import com.ling.jibonetposa.entities.iot.ResultUpdateNameEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTUpdateDevName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class UpdateDevNameModel extends BaseRequestModel<ResultUpdateNameEntity> {

    public UpdateDevNameModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void execute(PutUpdateDevNameEntity putUpdateNameEntity) {

        IIOTUpdateDevName iiotGetToken = retrofit().create(IIOTUpdateDevName.class);
        Call<Object> token = iiotGetToken.execute(organizeJsonParams(putUpdateNameEntity));
        token.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "executeSuccess: " + response.toString() + " / " + response.body().toString());
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "executeError: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                LingManager.getInstance().getLingLog().LOGD(TAG, "throwable: " + throwable.toString());
                mRequestCallback.responsedCallback(null, RETROFIT_ERROR, throwable);
            }
        });
    }
}
