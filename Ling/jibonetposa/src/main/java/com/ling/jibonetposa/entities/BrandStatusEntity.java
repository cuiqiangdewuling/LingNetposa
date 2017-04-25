package com.ling.jibonetposa.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.ling.jibonetposa.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mhz小志 on 2017/4/20.
 */

public class BrandStatusEntity extends BaseEntity implements Parcelable {

    private String userid;
    private List<Brand> brand_list;

    public BrandStatusEntity() {

    }

    protected BrandStatusEntity(Parcel in) {
        userid = in.readString();
        brand_list = in.createTypedArrayList(Brand.CREATOR);
    }

    public static final Creator<BrandStatusEntity> CREATOR = new Creator<BrandStatusEntity>() {
        @Override
        public BrandStatusEntity createFromParcel(Parcel in) {
            return new BrandStatusEntity(in);
        }

        @Override
        public BrandStatusEntity[] newArray(int size) {
            return new BrandStatusEntity[size];
        }
    };

    @Override
    public String toString() {
        return "BrandStatusEntity{" +
                "userid='" + userid + '\'' +
                ", brand_list=" + brand_list +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Brand> getBrand_list() {
        return brand_list;
    }

    public void setBrand_list(List<Brand> brand_list) {
        this.brand_list = brand_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userid);
        parcel.writeTypedList(brand_list);
    }

    public static class Brand implements Parcelable, Serializable {

        private String brand_id;
        private String brand_name;
        private int brand_status;

        public Brand() {

        }

        public Brand(String brand_id, String brand_name, int brand_status) {
            this.brand_id = brand_id;
            this.brand_name = brand_name;
            this.brand_status = brand_status;
        }

        protected Brand(Parcel in) {
            brand_id = in.readString();
            brand_name = in.readString();
            brand_status = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(brand_id);
            dest.writeString(brand_name);
            dest.writeInt(brand_status);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Brand> CREATOR = new Creator<Brand>() {
            @Override
            public Brand createFromParcel(Parcel in) {
                return new Brand(in);
            }

            @Override
            public Brand[] newArray(int size) {
                return new Brand[size];
            }
        };

        @Override
        public String toString() {
            return "Brand{" +
                    "brand_id='" + brand_id + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", brand_status=" + brand_status +
                    '}';
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public int getBrand_status() {
            return brand_status;
        }

        public void setBrand_status(int brand_status) {
            this.brand_status = brand_status;
        }
    }
}
