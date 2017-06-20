package com.ling.jibonetposa.entities.iot.scenario.config;

import java.util.Map;

/**
 * Created by mhz小志 on 2017/6/20.
 */

public class DeviceAction {

    private String device_id;
    private String type;
    private Map<String, String> config;

    public DeviceAction(String device_id, String type, Map<String, String> config) {
        this.device_id = device_id;
        this.type = type;
        this.config = config;
    }

    @Override
    public String toString() {
        return "DeviceAction{" +
                "device_id='" + device_id + '\'' +
                ", type='" + type + '\'' +
                ", config=" + config +
                '}';
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
