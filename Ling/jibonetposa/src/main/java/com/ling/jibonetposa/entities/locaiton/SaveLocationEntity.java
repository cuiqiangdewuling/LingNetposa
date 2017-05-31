package com.ling.jibonetposa.entities.locaiton;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class SaveLocationEntity extends BaseEntity {

    private Data data;

    public SaveLocationEntity(String userid, String province, String city, String latitude, String longitude) {
        this.data = new Data(new Attributes(userid, province, city, latitude, longitude));
    }

    @Override
    public String toString() {
        return "SaveLocationEntity{" +
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
        private String type = "geo";
        private Attributes attributes;

        public Data(Attributes attributes) {
            this.attributes = attributes;
        }


    }

    public static class Attributes {

        private String userid;
        private String province;
        private String city;
        private String latitude;
        private String longitude;

        @Override
        public String toString() {
            return "Attributes{" +
                    "userid='" + userid + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Attributes(String userid, String province, String city, String latitude, String longitude) {
            this.userid = userid;
            this.province = province;
            this.city = city;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
