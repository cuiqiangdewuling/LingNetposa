package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultScenarioSaveAllEntity extends BaseEntity {

    private int errno;
    private String errmsg;

    @Override
    public String toString() {
        return "ResultGetScenariosEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                '}';
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

        private String scenario_id;
        private List<Device> devices;

        @Override
        public String toString() {
            return "Data{" +
                    "scenario_id='" + scenario_id + '\'' +
                    ", devices=" + devices +
                    '}';
        }

        public String getScenario_id() {
            return scenario_id;
        }

        public void setScenario_id(String scenario_id) {
            this.scenario_id = scenario_id;
        }

        public List<Device> getDevices() {
            return devices;
        }

        public void setDevices(List<Device> devices) {
            this.devices = devices;
        }
    }

    public static class Device {

        private String device_id;
        private int code;

        @Override
        public String toString() {
            return "Device{" +
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
