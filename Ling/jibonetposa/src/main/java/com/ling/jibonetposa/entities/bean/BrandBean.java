package com.ling.jibonetposa.entities.bean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/22.
 */

public class BrandBean {

    private String key;
    private String name;
    private int code;
    private List<DeviceBean> val;
    private Permissions permissions;

    @Override
    public String toString() {
        return "BrandBean{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", val=" + val +
                ", permissions=" + permissions +
                '}';
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
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

    public static class Permissions {
        private boolean oauth;
        private boolean deviceList;
        private boolean deviceRename;
        private boolean scenarioList;
        private boolean deviceControl;

        @Override
        public String toString() {
            return "Permissions{" +
                    "oauth=" + oauth +
                    ", deviceList=" + deviceList +
                    ", deviceRename=" + deviceRename +
                    ", scenarioList=" + scenarioList +
                    ", deviceControl=" + deviceControl +
                    '}';
        }

        public boolean isOauth() {
            return oauth;
        }

        public void setOauth(boolean oauth) {
            this.oauth = oauth;
        }

        public boolean isDeviceList() {
            return deviceList;
        }

        public void setDeviceList(boolean deviceList) {
            this.deviceList = deviceList;
        }

        public boolean isDeviceRename() {
            return deviceRename;
        }

        public void setDeviceRename(boolean deviceRename) {
            this.deviceRename = deviceRename;
        }

        public boolean isScenarioList() {
            return scenarioList;
        }

        public void setScenarioList(boolean scenarioList) {
            this.scenarioList = scenarioList;
        }

        public boolean isDeviceControl() {
            return deviceControl;
        }

        public void setDeviceControl(boolean deviceControl) {
            this.deviceControl = deviceControl;
        }
    }
}
