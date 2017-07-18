package com.ling.jibonetposa.modules;

import android.text.TextUtils;

import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.userloop.SaveLoopsInfoModel;
import com.ling.jibonetposa.models.userloop.SaveUserInfoModel;

/**
 * Created by mhz小志 on 2017/7/15.
 */

public class UserInfoAgent {

    public void saveCurrentUser(String userInfo, IRequestCallback requestCallback) {
        if (!TextUtils.isEmpty(userInfo))
            new SaveUserInfoModel(requestCallback).saveUserInfo(userInfo);
    }

    public void saveCurrentLoops(String loopsInfo, IRequestCallback requestCallback) {
        if (!TextUtils.isEmpty(loopsInfo))
            new SaveLoopsInfoModel(requestCallback).saveLoopsInfo(loopsInfo);
    }
}
