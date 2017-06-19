package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/6/15.
 */

public class ScenarioDeletePOSTEntity extends BaseEntity {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ScenarioDeletePOSTEntity{" +
                "data=" + data +
                '}';
    }

    public ScenarioDeletePOSTEntity(String userid, String id) {
        this.data = new Data();
        this.data.setType("scenario-custom");
        this.data.setAttributes(new Attributes(userid,id));
    }

    public static class Data {
        private String type;
        private Attributes attributes;

        @Override
        public String toString() {
            return "Data{" +
                    "type='" + type + '\'' +
                    ", attributes=" + attributes +
                    '}';
        }

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
    }

    public static class Attributes {
        private String userid;
        private String id;

        public Attributes(String userid, String id) {
            this.userid = userid;
            this.id = id;
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

        @Override
        public String toString() {
            return "Attributes{" +
                    "userid='" + userid + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
