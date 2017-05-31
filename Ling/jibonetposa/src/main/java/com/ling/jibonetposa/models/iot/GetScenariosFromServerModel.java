package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.ResultGetScenariosEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetScenarios;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.IOTApiConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class GetScenariosFromServerModel extends BaseRequestModel<ResultGetScenariosEntity> {

    public GetScenariosFromServerModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void execute(String userId) {
        mParams.put("userid", userId);
        IIOTGetScenarios scenarios = retrofit().create(IIOTGetScenarios.class);
        Call<ResultGetScenariosEntity> call = scenarios.execute(organizeParams());
        execute(call);
    }
}
