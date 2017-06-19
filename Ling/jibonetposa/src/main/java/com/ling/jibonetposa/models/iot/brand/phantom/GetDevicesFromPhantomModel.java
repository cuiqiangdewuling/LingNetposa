package com.ling.jibonetposa.models.iot.brand.phantom;

import android.util.Log;

import com.google.gson.Gson;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.base.RetrofitException;
import com.ling.jibonetposa.entities.iot.PhantomDevicesEntity;
import com.ling.jibonetposa.entities.iot.PhantomDevicesEntity.PhantomDevice;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IIOTGetPhantomDevicrsFromPhantom;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_PHANTON_DEV;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class GetDevicesFromPhantomModel extends BaseRequestModel<PhantomDevicesEntity> {
    private PhantomDevicesEntity mPhantomDevices = new PhantomDevicesEntity();

    public GetDevicesFromPhantomModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_PHANTON_DEV;
    }

    public void getPhantomDevices(String accessToken) {
        IIOTGetPhantomDevicrsFromPhantom iiotGetToken = retrofit().create(IIOTGetPhantomDevicrsFromPhantom.class);
        Call<Object> token = iiotGetToken.execute("bearer " + accessToken);
        token.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    ArrayList body = (ArrayList) response.body();
                    Gson gson = new Gson();
                    mPhantomDevices.setList(new ArrayList<PhantomDevice>());
                    for (Object object : body) {
                        PhantomDevice devices = gson.fromJson(gson.toJson(object), PhantomDevice.class);
                        if ("curtain".equals(devices.getDevice_type()) || "nova2".equals(devices.getDevice_type())) {
                            mPhantomDevices.getList().add(devices);
                        }
                    }
                    mRequestCallback.responsedCallback(mPhantomDevices, RETROFIT_SUCCESS, (Throwable) null);
                } else {
                    Log.d("1111", "wrong" + response.toString());
                    mRequestCallback.responsedCallback(null, RETROFIT_WRONG, new RetrofitException(response.toString()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                Log.d("1111", "throwable" + throwable.toString());
                mRequestCallback.responsedCallback(null, RETROFIT_ERROR, throwable);
            }
        });
    }
}
