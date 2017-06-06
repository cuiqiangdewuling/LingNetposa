package com.ling.jibonetposa.entities.iot;

import android.os.Parcel;
import android.os.Parcelable;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/6/5.
 */
public class DeviceInfoEntity extends BaseEntity implements Parcelable {

    private String brand_id;
    private String device_id;
    private String device_ident;
    private String device_name;
    private String device_on;
    private String device_type;

    protected DeviceInfoEntity(Parcel in) {
        device_id = in.readString();
        brand_id = in.readString();
        device_ident = in.readString();
        device_name = in.readString();
        device_on = in.readString();
        device_type = in.readString();
    }

    protected DeviceInfoEntity() {
    }

    public DeviceInfoEntity(String brand_id, String device_id, String device_ident, String device_name, String device_on, String device_type) {
        this.device_id = device_id;
        this.brand_id = brand_id;
        this.device_ident = device_ident;
        this.device_name = device_name;
        this.device_on = device_on;
        this.device_type = device_type;
    }

    public static final Creator<DeviceInfoEntity> CREATOR = new Creator<DeviceInfoEntity>() {
        @Override
        public DeviceInfoEntity createFromParcel(Parcel in) {
            return new DeviceInfoEntity(in);
        }

        @Override
        public DeviceInfoEntity[] newArray(int size) {
            return new DeviceInfoEntity[size];
        }
    };

    @Override
    public String toString() {
        return "DeviceInfoEntity{" +
                "device_id='" + device_id + '\'' +
                ", brand_id='" + brand_id + '\'' +
                ", device_ident='" + device_ident + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_on='" + device_on + '\'' +
                ", device_type='" + device_type + '\'' +
                '}';
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getDevice_ident() {
        return device_ident;
    }

    public void setDevice_ident(String device_ident) {
        this.device_ident = device_ident;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_on() {
        return device_on;
    }

    public void setDevice_on(String device_on) {
        this.device_on = device_on;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(device_id);
        dest.writeString(brand_id);
        dest.writeString(device_ident);
        dest.writeString(device_name);
        dest.writeString(device_on);
        dest.writeString(device_type);
    }
}
