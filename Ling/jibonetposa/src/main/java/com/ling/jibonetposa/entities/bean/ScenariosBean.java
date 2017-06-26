package com.ling.jibonetposa.entities.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mhz小志 on 2017/4/22.
 */

public class ScenariosBean  implements Parcelable, Serializable {

    private String key;
    private String name;
    private int code;
    private List<DeviceBean> val;

    protected ScenariosBean(Parcel in) {
        key = in.readString();
        name = in.readString();
        code = in.readInt();
        val = in.createTypedArrayList(DeviceBean.CREATOR);
    }

    public static final Creator<ScenariosBean> CREATOR = new Creator<ScenariosBean>() {
        @Override
        public ScenariosBean createFromParcel(Parcel in) {
            return new ScenariosBean(in);
        }

        @Override
        public ScenariosBean[] newArray(int size) {
            return new ScenariosBean[size];
        }
    };

    @Override
    public String toString() {
        return "BrandBean{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", val=" + val +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int used) {
        this.code = used;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceBean> getVal() {
        return val;
    }

    public void setVal(List<DeviceBean> val) {
        this.val = val;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeInt(code);
        dest.writeTypedList(val);
    }
}
