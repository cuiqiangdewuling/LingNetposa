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
        private int sortno;
        private int class_id;
        private String title;
        private String summary;
        private String content;
        private String created_at;
        private String updated_at;

        @Override
        public String toString() {
            return "Information{" +
                    "id=" + id +
                    ", type=" + type +
                    ", sortno=" + sortno +
                    ", class_id=" + class_id +
                    ", title='" + title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", content='" + content + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    '}';
        }

        public int getSortno() {
            return sortno;
        }

        public void setSortno(int sortno) {
            this.sortno = sortno;
        }

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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
