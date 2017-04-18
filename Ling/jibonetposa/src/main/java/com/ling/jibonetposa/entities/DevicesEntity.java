package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/14.
 */

public class DevicesEntity extends BaseEntity {

    private List<Brand> brand_list;

    public List<Brand> getBrand_list() {
        return brand_list;
    }

    public void setBrand_list(List<Brand> brand_list) {
        this.brand_list = brand_list;
    }

    @Override
    public String toString() {
        return "DevicesEntity{" +
                "brand_list=" + brand_list +
                '}';
    }

    public static class Brand {

        private String brand_id;
        private String brand_name;
        private List<Device> device_list;

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

        public List<Device> getDevice_list() {
            return device_list;
        }

        public void setDevice_list(List<Device> device_list) {
            this.device_list = device_list;
        }

        @Override
        public String toString() {
            return "Brand{" +
                    "brand_id='" + brand_id + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", device_list=" + device_list +
                    '}';
        }

    }

    public static class Device {

        private String id;
        private String name;
        private String device_type;
        private String device_type_name;
        private String device_identifier;// 海尔改名用

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getDevice_identifier() {
            return device_identifier;
        }

        public void setDevice_identifier(String device_identifier) {
            this.device_identifier = device_identifier;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", device_type='" + device_type + '\'' +
                    ", device_type_name='" + device_type_name + '\'' +
                    ", device_identifier='" + device_identifier + '\'' +
                    '}';
        }
    }
}
/*
 {
    "data": {
        "brand_list": [
            {
                "brand_id": "abc123456",
                "brand_name": "huanteng",
                "device_list": [
                    {
                        "id": "123123",
                        "name": "客厅灯",
                        "device_type": "nova2",
                        "device_type_name": "ASDasd",
                        "device_identifier": "Qasd"
                    },
                    {
                        "id": "12123",
                        "name": "客厅空调",
                        "device_type": "nova2",
                        "device_type_name": "ASDasd",
                        "device_identifier": "Qasd"
                    }
                ]
            },
            {
                "brand_id": "abc123456",
                "brand_name": "huanteng",
                "device_list": [
                    {
                        "id": "123123",
                        "name": "客厅灯",
                        "device_type": "nova2",
                        "device_type_name": "ASDasd",
                        "device_identifier": "Qasd"
                    },
                    {
                        "id": "12123",
                        "name": "客厅空调",
                        "device_type": "nova2",
                        "device_type_name": "ASDasd",
                        "device_identifier": "Qasd"
                    }
                ]
            }
        ]
    }
}
 */