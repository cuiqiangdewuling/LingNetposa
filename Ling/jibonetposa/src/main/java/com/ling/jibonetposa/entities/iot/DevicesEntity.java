package com.ling.jibonetposa.entities.iot;

import android.os.Parcel;
import android.os.Parcelable;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.bean.BrandBean;
import com.ling.jibonetposa.entities.bean.DeviceBean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class DevicesEntity extends BaseEntity implements Parcelable {

    private String userid;
    private List<BrandBean> brand_list;
    private List<DeviceBean> repeatDev;// 重名的设备
    private List<DeviceBean> nameNotFitRulesDevList;// 名字不规范的设备
    private DeviceBean currentDev; // 用户当前选择的设备

    public DevicesEntity() {
    }

    protected DevicesEntity(Parcel in) {
        userid = in.readString();
        brand_list = in.createTypedArrayList(BrandBean.CREATOR);
        repeatDev = in.createTypedArrayList(DeviceBean.CREATOR);
        nameNotFitRulesDevList = in.createTypedArrayList(DeviceBean.CREATOR);
        currentDev = in.readParcelable(DeviceBean.class.getClassLoader());
    }

    public static final Creator<DevicesEntity> CREATOR = new Creator<DevicesEntity>() {
        @Override
        public DevicesEntity createFromParcel(Parcel in) {
            return new DevicesEntity(in);
        }

        @Override
        public DevicesEntity[] newArray(int size) {
            return new DevicesEntity[size];
        }
    };

    @Override
    public String toString() {
        return "DevicesEntity{" +
                "userid='" + userid + '\'' +
                ", brand_list=" + brand_list +
                ", repeatDev=" + repeatDev +
                ", currentDev=" + currentDev +
                '}';
    }

    public List<DeviceBean> getNameNotFitRulesDevList() {
        return nameNotFitRulesDevList;
    }

    public void setNameNotFitRulesDevList(List<DeviceBean> nameNotFitRulesDevList) {
        this.nameNotFitRulesDevList = nameNotFitRulesDevList;
    }

    public DeviceBean getCurrentDev() {
        return currentDev;
    }

    public void setCurrentDev(DeviceBean currentDev) {
        this.currentDev = currentDev;
    }

    public List<DeviceBean> getRepeatDev() {
        return repeatDev;
    }

    public void setRepeatDev(List<DeviceBean> repeatDev) {
        this.repeatDev = repeatDev;
    }

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userid);
        dest.writeTypedList(brand_list);
        dest.writeTypedList(repeatDev);
        dest.writeTypedList(nameNotFitRulesDevList);
        dest.writeParcelable(currentDev, flags);
    }
}
