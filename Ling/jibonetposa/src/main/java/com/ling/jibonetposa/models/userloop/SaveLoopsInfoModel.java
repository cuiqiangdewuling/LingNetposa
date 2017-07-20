package com.ling.jibonetposa.models.userloop;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.userloop.LoopsSaveEntity;
import com.ling.jibonetposa.entities.userloop.ResultuserLoopEntivity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.userloop.ISaveLoopsPOST;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class SaveLoopsInfoModel extends BaseRequestModel<ResultuserLoopEntivity> {

    public SaveLoopsInfoModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void saveLoopsInfo(Object attributes) {
        LoopsSaveEntity entity = new LoopsSaveEntity(attributes);
        ISaveLoopsPOST saveLoops = retrofit().create(ISaveLoopsPOST.class);
        Call<ResultuserLoopEntivity> call = saveLoops.execute(organizeJsonParams(entity));
        execute(call);
    }
}
