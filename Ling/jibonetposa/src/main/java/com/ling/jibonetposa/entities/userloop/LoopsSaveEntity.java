package com.ling.jibonetposa.entities.userloop;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/7/17.
 */

public class LoopsSaveEntity extends BaseEntity {

    private Data data;

    public LoopsSaveEntity(Object loopsInfo) {
        this.data = new Data();
        this.data.setAttributes(new Loops(loopsInfo));
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

    public static class Loops {

        private Object loops;

        public Loops() {
        }

        public Loops(Object loops) {
            this.loops = loops;
        }

        @Override
        public String toString() {
            return "Loops{" +
                    "setLoops=" + loops +
                    '}';
        }

        public Object getLoops() {
            return loops;
        }

        public void setLoops(Object loops) {
            this.loops = loops;
        }
    }

    public static class Data {

        private String type = "jibo";
        private Loops attributes;

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

        public Loops getAttributes() {
            return attributes;
        }

        public void setAttributes(Loops attributes) {
            this.attributes = attributes;
        }
    }

}
