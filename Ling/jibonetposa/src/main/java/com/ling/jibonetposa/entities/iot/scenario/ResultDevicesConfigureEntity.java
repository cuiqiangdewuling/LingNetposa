package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultDevicesConfigureEntity extends BaseEntity {

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

        private Object dictionaries;
        private Map<String, String> dictionariesMap;
        private List<Brand> brands;

        @Override
        public String toString() {
            return "Data{" +
                    "dictionaries=" + dictionaries +
                    ", dictionariesMap=" + dictionariesMap +
                    ", brands=" + brands +
                    '}';
        }

        public Object getDictionaries() {
            return dictionaries;
        }

        public void setDictionaries(Object dictionaries) {
            this.dictionaries = dictionaries;
        }

        public Map<String, String> getDictionariesMap() {
            return dictionariesMap;
        }

        public void setDictionariesMap(Map<String, String> dictionariesMap) {
            this.dictionariesMap = dictionariesMap;
        }

        public List<Brand> getBrands() {
            return brands;
        }

        public void setBrands(List<Brand> brands) {
            this.brands = brands;
        }
    }

    public static class Brand {
        private String brand_id;
        private Devices devices;

        @Override
        public String toString() {
            return "Brand{" +
                    "brand_id='" + brand_id + '\'' +
                    ", devices=" + devices +
                    '}';
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public Devices getDevices() {
            return devices;
        }

        public void setDevices(Devices devices) {
            this.devices = devices;
        }
    }

    public static class Devices {
        private Device light;
        private Device curtain;

        @Override
        public String toString() {
            return "Devices{" +
                    "light=" + light +
                    ", curtain=" + curtain +
                    '}';
        }

        public Device getLight() {
            return light;
        }

        public void setLight(Device light) {
            this.light = light;
        }

        public Device getCurtain() {
            return curtain;
        }

        public void setCurtain(Device curtain) {
            this.curtain = curtain;
        }
    }

    public static class Device {
        private DeviceParam onoff;
        private DeviceParam bright;
        private DeviceParam warm;
        private DeviceParam test01;
        private DeviceParam test02;

        @Override
        public String toString() {
            return "Device{" +
                    "test01=" + test01 +
                    "test02=" + test02 +
                    ", warm=" + warm +
                    ", bright=" + bright +
                    ", onoff=" + onoff +
                    '}';
        }

        public DeviceParam getOnoff() {
            return onoff;
        }

        public void setOnoff(DeviceParam onoff) {
            this.onoff = onoff;
        }

        public DeviceParam getBright() {
            return bright;
        }

        public void setBright(DeviceParam bright) {
            this.bright = bright;
        }

        public DeviceParam getWarm() {
            return warm;
        }

        public void setWarm(DeviceParam warm) {
            this.warm = warm;
        }

        public DeviceParam getTest01() {
            return test01;
        }

        public void setTest01(DeviceParam test01) {
            this.test01 = test01;
        }

        public DeviceParam getTest02() {
            return test02;
        }

        public void setTest02(DeviceParam test02) {
            this.test02 = test02;
        }
    }

    public static class DeviceParam {
        private String cname;
        private String display_type;
        private String value;
        private String default_value;

        @Override
        public String toString() {
            return "DeviceParam{" +
                    "cname='" + cname + '\'' +
                    ", display_type='" + display_type + '\'' +
                    ", value='" + value + '\'' +
                    ", default_value='" + default_value + '\'' +
                    '}';
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDefault_value() {
            return default_value;
        }

        public void setDefault_value(String default_value) {
            this.default_value = default_value;
        }
    }

}
