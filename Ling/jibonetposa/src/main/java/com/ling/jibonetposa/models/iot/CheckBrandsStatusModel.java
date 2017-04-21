package com.ling.jibonetposa.models.iot;

import android.os.Handler;
import android.os.Message;

import com.ling.jibonetposa.entities.DevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.util.ArrayList;
import java.util.List;

import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_BROADLINK;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_HAIER;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_NAME_PHANTOM;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_BROADLINK;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_HAIER;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_PHANTOM;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class CheckBrandsStatusModel {

    private String mUserId;
    private IRequestCallback mIRequestCallback;
    private DevicesEntity mDevicesEntity;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mIRequestCallback != null)
                mIRequestCallback.responsedCallback(mDevicesEntity, RETROFIT_SUCCESS, (Throwable) null);
        }
    };

    public CheckBrandsStatusModel(IRequestCallback requestCallback) {
        this.mIRequestCallback = requestCallback;
    }

    public void checkBrandStatus(String userId) {
        mUserId = userId;
        loadingBrandsData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }, 2000);
    }

    /**
     * 模拟网络请求
     */
    private void loadingBrandsData() {
        mDevicesEntity = new DevicesEntity();
        mDevicesEntity.setUserid(mUserId);
        List<DevicesEntity.Brand> brand_list = new ArrayList<>();
        DevicesEntity.Brand brand1 = new DevicesEntity.Brand();
        brand1.setBrand_name(BRAND_NAME_PHANTOM);
        brand1.setBrand_id(BRAND_TYPE_PHANTOM);
        brand_list.add(brand1);
        DevicesEntity.Brand brand2 = new DevicesEntity.Brand();
        brand2.setBrand_name(BRAND_NAME_HAIER);
        brand2.setBrand_id(BRAND_TYPE_HAIER);
        brand_list.add(brand2);
        DevicesEntity.Brand brand3 = new DevicesEntity.Brand();
        brand3.setBrand_name(BRAND_NAME_BROADLINK);
        brand3.setBrand_id(BRAND_TYPE_BROADLINK);
        brand_list.add(brand3);
        mDevicesEntity.setBrand_list(brand_list);
    }

}
