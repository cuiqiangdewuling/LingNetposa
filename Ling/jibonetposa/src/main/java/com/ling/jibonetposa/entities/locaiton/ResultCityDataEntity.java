package com.ling.jibonetposa.entities.locaiton;

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
        private LocationData attributes;

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

        public LocationData getAttributes() {
            return attributes;
        }

        public void setAttributes(LocationData attributes) {
            this.attributes = attributes;
        }
    }

    public static class LocationData {
        private List<String> hot;
        private List<Province> area;

        @Override
        public String toString() {
            return "LocationData{" +
                    "hot=" + hot +
                    ", area=" + area +
                    '}';
        }

        public List<String> getHot() {
            return hot;
        }

        public void setHot(List<String> hot) {
            this.hot = hot;
        }

        public List<Province> getArea() {
            return area;
        }

        public void setArea(List<Province> area) {
            this.area = area;
        }
    }

    public static class Province {
        private String name;
        private List<City> items;

        @Override
        public String toString() {
            return "Province{" +
                    "items=" + items +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getItems() {
            return items;
        }

        public void setItems(List<City> items) {
            this.items = items;
        }
    }


    public static class City {
        private String name;

        private List<Area> items;

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", items=" + items +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Area> getItems() {
            return items;
        }

        public void setItems(List<Area> items) {
            this.items = items;
        }
    }

    public static class Area {
        private String name;

        @Override
        public String toString() {
            return "Area{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
