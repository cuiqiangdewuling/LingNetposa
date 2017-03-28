package com.ling.jibonetposa;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ling.jibonetposa.handler.SafeHandler;

/**
 * Created by cuiqiang on 2017/3/27.
 */

public class BaseFragment extends Fragment implements SafeHandler.IHandlerMessage{

    protected String mUniqueIdentifier = "";
    protected Handler mDefaultHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUniqueIdentifier = String.valueOf(this);
        mDefaultHandler = new SafeHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void handlerMessage(Message msg) {}

    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }
}
