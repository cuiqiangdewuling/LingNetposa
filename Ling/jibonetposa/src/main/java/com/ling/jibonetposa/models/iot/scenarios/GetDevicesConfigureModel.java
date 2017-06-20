package com.ling.jibonetposa.models.iot.scenarios;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.scenario.ResultDevicesConfigureEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetDevicesConfigure;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class GetDevicesConfigureModel extends BaseRequestModel<ResultDevicesConfigureEntity> {

    public GetDevicesConfigureModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void execute() {
        IIOTGetDevicesConfigure scenarios = retrofit().create(IIOTGetDevicesConfigure.class);
        Call<ResultDevicesConfigureEntity> call = scenarios.execute();
        execute(call);
    }
}
