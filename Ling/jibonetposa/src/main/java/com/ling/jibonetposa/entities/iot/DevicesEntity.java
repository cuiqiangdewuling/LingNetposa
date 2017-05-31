package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.bean.BrandBean;
import com.ling.jibonetposa.entities.bean.DeviceBean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class DevicesEntity extends BaseEntity {

    private String userid;
    private List<BrandBean> brand_list;
    private List<DeviceBean> repeatDev;// 重名的设备

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<BrandBean> getBrand_list() {
        return brand_list;
    }

    public void setBrand_list(List<BrandBean> brand_list) {
        this.brand_list = brand_list;
    }

    @Override
    public String toString() {
        return "DevicesEntity{" +
                "userid='" + userid + '\'' +
                ", brand_list=" + brand_list +
                '}';
    }


}
