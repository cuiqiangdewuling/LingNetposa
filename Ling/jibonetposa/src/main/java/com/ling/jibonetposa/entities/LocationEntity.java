package com.ling.jibonetposa.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/5/10.
 */

public class LocationEntity extends BaseEntity implements Parcelable {

    private String city;

    public LocationEntity() {
    }

    protected LocationEntity(Parcel in) {
        city = in.readString();
    }

    public static final Creator<LocationEntity> CREATOR = new Creator<LocationEntity>() {
        @Override
        public LocationEntity createFromParcel(Parcel in) {
            return new LocationEntity(in);
        }

        @Override
        public LocationEntity[] newArray(int size) {
            return new LocationEntity[size];
        }
    };

    @Override
    public String toString() {
        return "LocationEntity{" +
                "city='" + city + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
    }
}
