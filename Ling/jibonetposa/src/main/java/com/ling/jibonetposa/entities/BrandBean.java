package com.ling.jibonetposa.entities;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/22.
 */

public class BrandBean {

    private String key;
    private String name;
    private int used;
    private List<DeviceBean> val;

    @Override
    public String toString() {
        return "BrandBean{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", used=" + used +
                ", val=" + val +
                '}';
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
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
}
