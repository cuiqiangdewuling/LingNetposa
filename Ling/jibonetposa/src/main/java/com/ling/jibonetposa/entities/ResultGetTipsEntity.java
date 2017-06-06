package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class ResultGetTipsEntity extends BaseEntity {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultGetTokenEntity{" +
                ", data=" + data +
                '}';
    }

    public static class Data {
        private String type;
        private String id;
        private Attributes attributes;

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
        private List<Information> informations;

        @Override
        public String toString() {
            return "Attributes{" +
                    "informations=" + informations +
                    '}';
        }

        public List<Information> getInformations() {
            return informations;
        }

        public void setInformations(List<Information> informations) {
            this.informations = informations;
        }
    }

    public static class Information {
        private int id;
        private int type;
        private String title;
        private String content;

        @Override
        public String toString() {
            return "Information{" +
                    "id=" + id +
                    ", type=" + type +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
