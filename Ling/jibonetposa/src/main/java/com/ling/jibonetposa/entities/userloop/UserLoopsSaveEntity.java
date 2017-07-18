package com.ling.jibonetposa.entities.userloop;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/7/17.
 */

public class UserLoopsSaveEntity extends BaseEntity {

    private Data data;

    public UserLoopsSaveEntity(String type, String attributes) {
        this.data = new Data();
        this.data.setType(type);
        this.data.setAttributes(attributes);
    }

    @Override
    public String toString() {
        return "ResultuserLoopEntivity{" +
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
        private String attributes;

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

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }
    }

}
