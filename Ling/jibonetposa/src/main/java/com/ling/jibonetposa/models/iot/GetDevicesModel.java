package com.ling.jibonetposa.models.iot;

import android.os.Handler;
import android.os.Message;

import com.ling.jibonetposa.entities.DevicesEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.util.ArrayList;
import java.util.List;

import static com.ling.jibonetposa.base.BaseRequestModel.RETROFIT_SUCCESS;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_BROADLINK;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_HAIER;
import static com.ling.jibonetposa.constants.IOTDevConstant.BRAND_TYPE_PHANTOM;

/**
 * Created by mhz小志 on 2017/4/18.
 */
public class GetDevicesModel {

    private IRequestCallback mIRequestCallback;
    private DevicesEntity mDevicesEntity;
    private String mUserId;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mIRequestCallback != null)
                mIRequestCallback.responsedCallback(mDevicesEntity, RETROFIT_SUCCESS, (Throwable) null);
        }
    };

    public GetDevicesModel(IRequestCallback requestCallback) {
        this.mIRequestCallback = requestCallback;
    }

    public void getDevices(String userId) {
        mUserId = userId;
        loadingDevicesData();
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
    private void loadingDevicesData() {
        mDevicesEntity = new DevicesEntity();
        mDevicesEntity.setUserid(mUserId);
        List<DevicesEntity.Brand> brand_list = new ArrayList<>();
// ================================ 幻腾 ================================
        DevicesEntity.Brand brand1 = new DevicesEntity.Brand();
        brand1.setBrand_name("幻腾");
        brand1.setBrand_id(BRAND_TYPE_PHANTOM);
        ArrayList<DevicesEntity.DeviceItem> devices1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            devices1.add(new DevicesEntity.DeviceItem("幻腾设备" + (i + 1)));
        }
        brand1.setDevice_list(devices1);
        brand_list.add(brand1);
// ================================ 海尔 ================================
        DevicesEntity.Brand brand2 = new DevicesEntity.Brand();
        brand2.setBrand_name("海尔");
        brand2.setBrand_id(BRAND_TYPE_HAIER);
        ArrayList<DevicesEntity.DeviceItem> devices2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            devices2.add(new DevicesEntity.DeviceItem("海尔设备" + (i + 1)));
        }
        brand2.setDevice_list(devices2);
        brand_list.add(brand2);
// ================================ 博联 ================================
        DevicesEntity.Brand brand3 = new DevicesEntity.Brand();
        brand3.setBrand_name("博联");
        brand3.setBrand_id(BRAND_TYPE_BROADLINK);
        ArrayList<DevicesEntity.DeviceItem> devices3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            devices3.add(new DevicesEntity.DeviceItem("博联设备" + (i + 1)));
        }
        brand3.setDevice_list(devices3);
        brand_list.add(brand3);
        mDevicesEntity.setBrand_list(brand_list);
    }

}
