package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/6/15.
 */

public class ScenarioDeviceEditPOSTEntity extends BaseEntity {

    private Data data;

    public ScenarioDeviceEditPOSTEntity(String userid, String id, String device_id, String type, Object control_config) {
        this.data = new Data();
        this.data.setType("scenario-custom-devices");
        this.data.setAttributes(new Attributes(userid, id, device_id, type, control_config));
    }

    @Override
    public String toString() {
        return "ScenarioCreatePOSTEntity{" +
                "data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String type;
        private Attributes attributes;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "type='" + type + '\'' +
                    ", attributes=" + attributes +
                    '}';
        }
    }

    
    public static class Attributes {
        private String userid;
        private String id;
        private String device_id;
        private String type;
        private Object control_config;

        public Attributes(String userid, String id, String device_id, String type, Object control_config) {
            this.userid = userid;
            this.id = id;
            this.device_id = device_id;
            this.type = type;
            this.control_config = control_config;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "userid='" + userid + '\'' +
                    ", id='" + id + '\'' +
                    ", device_id='" + device_id + '\'' +
                    ", type='" + type + '\'' +
                    ", control_config=" + control_config +
                    '}';
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Object getControl_config() {
            return control_config;
        }

        public void setControl_config(Object control_config) {
            this.control_config = control_config;
        }
    }
}
