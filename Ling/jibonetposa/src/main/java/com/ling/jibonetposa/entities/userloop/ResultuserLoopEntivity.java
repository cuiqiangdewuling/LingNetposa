package com.ling.jibonetposa.entities.userloop;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/7/17.
 */

public class ResultuserLoopEntivity extends BaseEntity {

    private Data data;

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
        private    String type;
        private   String id;
        private   Attributes attributes;

        @Override
        public String toString() {
            return "Data{" +
                    "type='" + type + '\'' +
                    ", id='" + id + '\'' +
                    ", attributes=" + attributes +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }
    }

    public static class Attributes {
        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "result=" + result +
                    '}';
        }
    }
}
