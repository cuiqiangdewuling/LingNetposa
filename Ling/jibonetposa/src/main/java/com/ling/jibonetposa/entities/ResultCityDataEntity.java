package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class ResultCityDataEntity extends BaseEntity {

    private Data data;

    @Override
    public String toString() {
        return "ResultLocationEntity{" +
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
        private String id;
        private List<CityData> attributes;

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

        public List<CityData> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<CityData> attributes) {
            this.attributes = attributes;
        }
    }

    public static class CityData {

        public String key;
        public List<String> items;

        @Override
        public String toString() {
            return "CityData{" +
                    "key='" + key + '\'' +
                    ", items=" + items +
                    '}';
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
    }
}
