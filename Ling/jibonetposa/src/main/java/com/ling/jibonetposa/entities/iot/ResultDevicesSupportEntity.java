package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultDevicesSupportEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private List<Data> data;

    @Override
    public String toString() {
        return "ResultDevicesSupportEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        String device_type;
        String device_type_name;
        String brand_id;

        @Override
        public String toString() {
            return "Data{" +
                    "brand_id='" + brand_id + '\'' +
                    ", device_type_name='" + device_type_name + '\'' +
                    ", device_type='" + device_type + '\'' +
                    '}';
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDevice_type_name() {
            return device_type_name;
        }

        public void setDevice_type_name(String device_type_name) {
            this.device_type_name = device_type_name;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }
    }

}
