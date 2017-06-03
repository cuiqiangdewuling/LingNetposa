package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.PutUpdateDevNameEntity;
import com.ling.jibonetposa.entities.iot.ResultUpdateNameEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTUpdateDevName;

import retrofit2.Call;

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
        Call<ResultUpdateNameEntity> call = iiotGetToken.execute(organizeJsonParams(putUpdateNameEntity));
        execute(call);
    }
}
