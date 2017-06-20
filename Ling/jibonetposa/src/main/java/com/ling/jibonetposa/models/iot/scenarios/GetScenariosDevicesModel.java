package com.ling.jibonetposa.models.iot.scenarios;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.scenario.ResultScenarioGetDevEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IScenarioDevicesGET;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class GetScenariosDevicesModel extends BaseRequestModel<ResultScenarioGetDevEntity> {

    public GetScenariosDevicesModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void execute(String userId, String id) {
        mParams.put("userid", userId);
        mParams.put("id", id);
        IScenarioDevicesGET scenarios = retrofit().create(IScenarioDevicesGET.class);
        Call<ResultScenarioGetDevEntity> call = scenarios.execute(organizeParams());
        execute(call);
    }
}
