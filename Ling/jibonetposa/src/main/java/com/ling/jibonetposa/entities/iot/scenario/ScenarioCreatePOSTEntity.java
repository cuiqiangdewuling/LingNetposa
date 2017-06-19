package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/6/15.
 */

public class ScenarioCreatePOSTEntity extends BaseEntity {

    private Data data;

    public ScenarioCreatePOSTEntity(String userid, String name, String image_type) {
        this.data = new Data();
        this.data.setType("scenario-custom");
        this.data.setAttributes(new Attributes(userid, name, image_type));
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
        private String name;
        private String image_type;

        public Attributes(String userid, String name, String image_type) {
            this.userid = userid;
            this.name = name;
            this.image_type = image_type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_type() {
            return image_type;
        }

        public void setImage_type(String image_type) {
            this.image_type = image_type;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "userid='" + userid + '\'' +
                    ", name='" + name + '\'' +
                    ", image_type='" + image_type + '\'' +
                    '}';
        }
    }
}
