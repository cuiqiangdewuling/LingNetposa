package com.ling.jibonetposa.entities.bean;

import com.ling.jibonetposa.entities.iot.scenario.config.DeviceConfigBean;

/**
 * Created by mhz小志 on 2017/4/22.
 */

public class DeviceBean {

    private String device_id;
    private String device_ident;
    private String device_name;
    private String device_on;
    private String device_type;
    private String brand_id;
    private String brand_name;
    private String device_flag;
    private int image_type;
    private int device_code;  // 0: 正常显示    1: 设备名称不符合规则    2：改名失败   1:不支持改名
    private int brand_status;
    private int id;
    private DeviceConfigBean control_config;

    @Override
    public String toString() {
        return "DeviceBean{" +
                "device_id='" + device_id + '\'' +
                ", device_ident='" + device_ident + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_on='" + device_on + '\'' +
                ", device_type='" + device_type + '\'' +
                ", brand_id='" + brand_id + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", device_flag='" + device_flag + '\'' +
                ", image_type=" + image_type +
                ", device_code=" + device_code +
                ", brand_status=" + brand_status +
                ", id=" + id +
                ", control_config=" + control_config +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_flag() {
        return device_flag;
    }

    public void setDevice_flag(String device_flag) {
        this.device_flag = device_flag;
    }

    public DeviceConfigBean getControl_config() {
        return control_config;
    }

    public void setControl_config(DeviceConfigBean control_config) {
        this.control_config = control_config;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public int getDevice_code() {
        return device_code;
    }

    public void setDevice_code(int device_code) {
        this.device_code = device_code;
    }

    public int getImage_type() {
        return image_type;
    }

    public void setImage_type(int image_type) {
        this.image_type = image_type;
    }


    public int getBrand_status() {
        return brand_status;
    }

    public void setBrand_status(int brand_status) {
        this.brand_status = brand_status;
    }

    public DeviceBean() {
    }

    public DeviceBean(String device_name) {
        this.device_name = device_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceBean that = (DeviceBean) o;

        return device_name.equals(that.device_name);

    }

    @Override
    public int hashCode() {
        return device_name.hashCode();
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
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
}
