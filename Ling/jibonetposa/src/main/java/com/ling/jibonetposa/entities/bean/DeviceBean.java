package com.ling.jibonetposa.entities.bean;

/**
 * Created by mhz小志 on 2017/4/22.
 */

public class DeviceBean {

    private String device_id;
    private String device_ident;
    private String device_name;
    private String device_on;
    private String device_type;

    @Override
    public String toString() {
        return "DeviceBean{" +
                "device_id='" + device_id + '\'' +
                ", device_ident='" + device_ident + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_on='" + device_on + '\'' +
                ", device_type='" + device_type + '\'' +
                '}';
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
