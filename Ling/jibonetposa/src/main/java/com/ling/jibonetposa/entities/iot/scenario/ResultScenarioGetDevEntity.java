package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.bean.DeviceBean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultScenarioGetDevEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private Data data;


    @Override
    public String toString() {
        return "ResultScenarioGetDevEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static class Data {
        private List<DeviceBean> devices;

        @Override
        public String toString() {
            return "Data{" +
                    "devices=" + devices +
                    '}';
        }

        public List<DeviceBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DeviceBean> devices) {
            this.devices = devices;
        }
    }

}
