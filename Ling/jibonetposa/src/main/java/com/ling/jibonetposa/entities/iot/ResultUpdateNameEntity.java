package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class ResultUpdateNameEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private List<Item> data;

    @Override
    public String toString() {
        return "ResultUpdateNameEntity{" +
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

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public static class Item {
        private String device_id;
        private int code;

        @Override
        public String toString() {
            return "Item{" +
                    "device_id='" + device_id + '\'' +
                    ", code=" + code +
                    '}';
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

}
