package com.ling.jibonetposa.models.userloop;

import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.userloop.ResultuserLoopEntivity;
import com.ling.jibonetposa.entities.userloop.UserSaveEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.userloop.ISaveUserPOST;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_JIBO;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class SaveUserInfoModel extends BaseRequestModel<ResultuserLoopEntivity> {

    public SaveUserInfoModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_JIBO;
    }

    public void saveUserInfo(Object userInfo) {
        UserSaveEntity entity = new UserSaveEntity(userInfo);
        ISaveUserPOST saveUser = retrofit().create(ISaveUserPOST.class);
        Call<ResultuserLoopEntivity> call = saveUser.execute(organizeJsonParams(entity));
        execute(call);
    }
}
