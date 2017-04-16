package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/14.
 */

public class PhantomDevicesEntity extends BaseEntity {

    private List<PhantomDevice> mList;

    @Override
    public String toString() {
        return "PhantomDevicesEntity{" +
                "mList=" + mList +
                '}';
    }

    public List<PhantomDevice> getList() {
        return mList;
    }

    public void setList(List<PhantomDevice> list) {
        mList = list;
    }

    public static class PhantomDevice {

//        public Object mObject;

        private int id;
        private String device_type; // nova2  curtain
        private String device_type_words; // nova2  curtain
        private String name;

        @Override
        public String toString() {
            return "PhantomDevicesEntity{" +
                    "id=" + id +
                    ", device_type='" + device_type + '\'' +
                    ", device_type_words='" + device_type_words + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDevice_type_words() {
            return device_type_words;
        }

        public void setDevice_type_words(String device_type_words) {
            this.device_type_words = device_type_words;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
