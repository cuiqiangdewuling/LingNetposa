package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class PutUpdateDevNameEntity extends BaseEntity {

    private Data data;

    public PutUpdateDevNameEntity(String userid, List<Item> devices) {
        data = new Data("devices", new DevicesData(userid, devices));
    }

    public PutUpdateDevNameEntity() {
    }

    @Override
    public String toString() {
        return "ResultLocationEntity{" +
                "data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String type;
        private DevicesData attributes;

        public Data() {
        }

        public Data(String type, DevicesData attributes) {
            this.type = type;
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "type='" + type + '\'' +
                    ", id='" + id + '\'' +
                    ", attributes=" + attributes +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DevicesData getAttributes() {
            return attributes;
        }

        public void setAttributes(DevicesData attributes) {
            this.attributes = attributes;
        }
    }

    public static class DevicesData {
        private String userid;
        private List<Item> devices;

        public DevicesData(String userid, List<Item> devices) {
            this.userid = userid;
            this.devices = devices;
        }

        public DevicesData() {

        }

        @Override
        public String toString() {
            return "DevicesData{" +
                    "userid='" + userid + '\'' +
                    ", devices=" + devices +
                    '}';
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public List<Item> getDevices() {
            return devices;
        }

        public void setDevices(List<Item> devices) {
            this.devices = devices;
        }
    }

    public static class Item {
        private String device_id;
        private String device_name;

        public Item() {
        }

        public Item(String device_id, String device_name) {
            this.device_id = device_id;
            this.device_name = device_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "device_id='" + device_id + '\'' +
                    ", device_name='" + device_name + '\'' +
                    '}';
        }
    }
}
