package com.ling.jibonetposa.entities.iot.scenario.config;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mhz小志 on 2017/6/20.
 */

public class DeviceConfigBean   implements Parcelable, Serializable {

    private String onoff = "NONE";
    private String bright = "NONE";
    private String warm = "NONE";
    private String test01 = "NONE";
    private String test02 = "NONE";

    protected DeviceConfigBean(Parcel in) {
        onoff = in.readString();
        bright = in.readString();
        warm = in.readString();
        test01 = in.readString();
        test02 = in.readString();
    }

    public static final Creator<DeviceConfigBean> CREATOR = new Creator<DeviceConfigBean>() {
        @Override
        public DeviceConfigBean createFromParcel(Parcel in) {
            return new DeviceConfigBean(in);
        }

        @Override
        public DeviceConfigBean[] newArray(int size) {
            return new DeviceConfigBean[size];
        }
    };

    @Override
    public String toString() {
        return "DeviceConfigBean{" +
                "onoff='" + onoff + '\'' +
                ", bright='" + bright + '\'' +
                ", warm='" + warm + '\'' +
                ", test01='" + test01 + '\'' +
                ", test02='" + test02 + '\'' +
                '}';
    }

    public String getOnoff() {
        return onoff;
    }

    public void setOnoff(String onoff) {
        this.onoff = onoff;
    }

    public String getBright() {
        return bright;
    }

    public void setBright(String bright) {
        this.bright = bright;
    }

    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }

    public String getTest01() {
        return test01;
    }

    public void setTest01(String test01) {
        this.test01 = test01;
    }

    public String getTest02() {
        return test02;
    }

    public void setTest02(String test02) {
        this.test02 = test02;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(onoff);
        dest.writeString(bright);
        dest.writeString(warm);
        dest.writeString(test01);
        dest.writeString(test02);
    }
}
