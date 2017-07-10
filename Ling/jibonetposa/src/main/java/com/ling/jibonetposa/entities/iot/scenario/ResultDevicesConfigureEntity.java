package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.HashMap;
import java.util.List;

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

        private HashMap<String, String> dictionaries;
        private List<Brand> brands;

        @Override
        public String toString() {
            return "Data{" +
                    "dictionaries=" + dictionaries +
                    ", brands=" + brands +
                    '}';
        }

        public HashMap getDictionaries() {
            return dictionaries;
        }

        public void setDictionaries(HashMap dictionaries) {
            this.dictionaries = dictionaries;
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
        private Device socket;
        private Device backgroundmusic;
        private Device QBL;
        private Device washingmachine;
        private Device exhausthood;
        private Device waterheater;
        private Device xinfeng;
        private Device underfloorheating;
        private Device TV;
        private Device winecabinet;
        private Device aircondition;
        private Device infrared;
        private Device humidifier;
        private Device aircleaner;

        @Override
        public String toString() {
            return "Devices{" +
                    "\n light=" + light +
                    ",\n curtain=" + curtain +
                    ",\n socket=" + socket +
                    ",\n backgroundmusic=" + backgroundmusic +
                    ",\n QBL=" + QBL +
                    ",\n washingmachine=" + washingmachine +
                    ",\n exhausthood=" + exhausthood +
                    ",\n waterheater=" + waterheater +
                    ",\n xinfeng=" + xinfeng +
                    ",\n underfloorheating=" + underfloorheating +
                    ",\n TV=" + TV +
                    ",\n winecabinet=" + winecabinet +
                    ",\n aircondition=" + aircondition +
                    ",\n infrared=" + infrared +
                    ",\n humidifier=" + humidifier +
                    ",\n aircleaner=" + aircleaner +
                    '}';
        }

        public Device getInfrared() {
            return infrared;
        }

        public void setInfrared(Device infrared) {
            this.infrared = infrared;
        }

        public Device getHumidifier() {
            return humidifier;
        }

        public void setHumidifier(Device humidifier) {
            this.humidifier = humidifier;
        }

        public Device getAircleaner() {
            return aircleaner;
        }

        public void setAircleaner(Device aircleaner) {
            this.aircleaner = aircleaner;
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

        public Device getSocket() {
            return socket;
        }

        public void setSocket(Device socket) {
            this.socket = socket;
        }

        public Device getBackgroundmusic() {
            return backgroundmusic;
        }

        public void setBackgroundmusic(Device backgroundmusic) {
            this.backgroundmusic = backgroundmusic;
        }

        public Device getQBL() {
            return QBL;
        }

        public void setQBL(Device QBL) {
            this.QBL = QBL;
        }

        public Device getWashingmachine() {
            return washingmachine;
        }

        public void setWashingmachine(Device washingmachine) {
            this.washingmachine = washingmachine;
        }

        public Device getExhausthood() {
            return exhausthood;
        }

        public void setExhausthood(Device exhausthood) {
            this.exhausthood = exhausthood;
        }

        public Device getWaterheater() {
            return waterheater;
        }

        public void setWaterheater(Device waterheater) {
            this.waterheater = waterheater;
        }

        public Device getXinfeng() {
            return xinfeng;
        }

        public void setXinfeng(Device xinfeng) {
            this.xinfeng = xinfeng;
        }

        public Device getUnderfloorheating() {
            return underfloorheating;
        }

        public void setUnderfloorheating(Device underfloorheating) {
            this.underfloorheating = underfloorheating;
        }

        public Device getTV() {
            return TV;
        }

        public void setTV(Device TV) {
            this.TV = TV;
        }

        public Device getWinecabinet() {
            return winecabinet;
        }

        public void setWinecabinet(Device winecabinet) {
            this.winecabinet = winecabinet;
        }

        public Device getAircondition() {
            return aircondition;
        }

        public void setAircondition(Device aircondition) {
            this.aircondition = aircondition;
        }
    }


    public static class Device {
        private DeviceParam onoff;
        private HashMap<String, DeviceParam> status;

        @Override
        public String toString() {
            return "Device{" +
                    "onoff=" + onoff +
                    ", status=" + status +
                    '}';
        }

        public DeviceParam getOnoff() {
            return onoff;
        }

        public void setOnoff(DeviceParam onoff) {
            this.onoff = onoff;
        }

        public HashMap<String, DeviceParam> getStatus() {
            return status;
        }

        public void setStatus(HashMap<String, DeviceParam> status) {
            this.status = status;
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
            try {
                int val = Integer.valueOf(default_value);
                if ("input".equals(display_type) || "slider".equals(display_type)) {
                    String[] split = value.split("\\|");
                    if (split.length >= 2) {
                        int a = 0, b = 0;
                        int min = 0, max = 0;
                        a = Integer.valueOf(split[0]);
                        b = Integer.valueOf(split[1]);
                        if (a < b) {
                            min = a;
                            max = b;
                        } else {
                            min = b;
                            max = a;
                        }
                        if (val < min) {
                            val = min;
                        } else if (val > max) {
                            val = max;
                        }
                    }
                }
                return String.valueOf(val);
            } catch (Exception e) {
                return default_value;
            }
        }

        public void setDefault_value(String default_value) {
            this.default_value = default_value;
        }
    }

}
