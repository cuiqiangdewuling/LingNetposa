package com.ling.jibonetposa.modules;

import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.userloop.SaveLoopsInfoModel;
import com.ling.jibonetposa.models.userloop.SaveUserInfoModel;

/**
 * Created by mhz小志 on 2017/7/15.
 */

public class UserInfoAgent {

    public void saveCurrentUser(Object userInfo, IRequestCallback requestCallback) {
        if (userInfo != null)
            new SaveUserInfoModel(requestCallback).saveUserInfo(userInfo);
    }

    public void saveCurrentLoops(Object loopsInfo, IRequestCallback requestCallback) {
        if (loopsInfo != null)
            new SaveLoopsInfoModel(requestCallback).saveLoopsInfo(loopsInfo);
    }
}
