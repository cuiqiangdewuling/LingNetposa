package com.ling.jibonetposa.entities.userloop;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/7/17.
 */

public class UserSaveEntity extends BaseEntity {

    private Data data;

    public UserSaveEntity(Object userInfo) {
        this.data = new Data();
        this.data.setAttributes(userInfo);
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
        private String type ="jibo-user";
        private Object attributes;

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

        public Object getAttributes() {
            return attributes;
        }

        public void setAttributes(Object attributes) {
            this.attributes = attributes;
        }
    }

}
