package com.ling.jibonetposa.models.iot.scenarios;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.scenario.ResultScenarioSaveAllEntity;
import com.ling.jibonetposa.entities.iot.scenario.ScenarioDeletePOSTEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IScenarioSaveAllPOST;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class PostScenarioDeleteModel extends BaseRequestModel<ResultScenarioSaveAllEntity> {

    public PostScenarioDeleteModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void execute(ScenarioDeletePOSTEntity saveallpostentity) {
        IScenarioSaveAllPOST iiotGetToken = retrofit().create(IScenarioSaveAllPOST.class);
        Call<ResultScenarioSaveAllEntity> call = iiotGetToken.execute(organizeJsonParams(saveallpostentity));
        execute(call);
    }
}
