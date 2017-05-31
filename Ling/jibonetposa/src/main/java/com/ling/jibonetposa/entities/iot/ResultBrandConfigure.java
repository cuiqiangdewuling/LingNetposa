package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/5/25.
 */

public class ResultBrandConfigure extends BaseEntity {

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
        private Phantom huateng;
        private Haier haier;
        private Broadlink broadlink;
        private MIJIA mijia;

        @Override
        public String toString() {
            return "Attributes{" +
                    "huateng=" + huateng +
                    ", haier=" + haier +
                    ", broadlink=" + broadlink +
                    ", mijia=" + mijia +
                    '}';
        }

        public Phantom getHuateng() {
            return huateng;
        }

        public void setHuateng(Phantom huateng) {
            this.huateng = huateng;
        }

        public Haier getHaier() {
            return haier;
        }

        public void setHaier(Haier haier) {
            this.haier = haier;
        }

        public Broadlink getBroadlink() {
            return broadlink;
        }

        public void setBroadlink(Broadlink broadlink) {
            this.broadlink = broadlink;
        }

        public MIJIA getMijia() {
            return mijia;
        }

        public void setMijia(MIJIA mijia) {
            this.mijia = mijia;
        }
    }

    public static class Phantom {
        private String url;
        private String client_id;
        private String client_secret;
        private String scope;
        private String redirect_url;

        @Override
        public String toString() {
            return "Phantom{" +
                    "url='" + url + '\'' +
                    ", client_id='" + client_id + '\'' +
                    ", client_secret='" + client_secret + '\'' +
                    ", scope='" + scope + '\'' +
                    ", redirect_url='" + redirect_url + '\'' +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }
    }

    public static class Haier {
        private HEAndroid android;
        private HEIOS ios;


        @Override
        public String toString() {
            return "Haier{" +
                    "android=" + android +
                    ", ios=" + ios +
                    '}';
        }


        public HEAndroid getAndroid() {
            return android;
        }

        public void setAndroid(HEAndroid android) {
            this.android = android;
        }

        public HEIOS getIos() {
            return ios;
        }

        public void setIos(HEIOS ios) {
            this.ios = ios;
        }
    }

    public static class Broadlink {
        private String url;
        private String token_get;
        private String client_id;
        private String client_secret;
        private String lid;
        private String license;
        private String redirect_url;

        @Override
        public String toString() {
            return "Broadlink{" +
                    "url='" + url + '\'' +
                    ", token_get='" + token_get + '\'' +
                    ", client_id='" + client_id + '\'' +
                    ", client_secret='" + client_secret + '\'' +
                    ", lid='" + lid + '\'' +
                    ", license='" + license + '\'' +
                    ", redirect_url='" + redirect_url + '\'' +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getToken_get() {
            return token_get;
        }

        public void setToken_get(String token_get) {
            this.token_get = token_get;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }
    }

    public static class MIJIA {
        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        @Override

        public String toString() {
            return "MIJIA{" +
                    "object=" + object +
                    '}';
        }
    }

    public static class HEAndroid {
        private String app_key;
        private String app_secret;

        public String getApp_key() {
            return app_key;
        }

        public void setApp_key(String app_key) {
            this.app_key = app_key;
        }

        public String getApp_secret() {
            return app_secret;
        }

        public void setApp_secret(String app_secret) {
            this.app_secret = app_secret;
        }

        @Override
        public String toString() {
            return "HEAndroid{" +
                    "app_key='" + app_key + '\'' +
                    ", app_secret='" + app_secret + '\'' +
                    '}';
        }
    }

    public static class HEIOS {
        private String url;
        private String appId;
        private String appKey;

        @Override
        public String toString() {
            return "HEIOS{" +
                    "url='" + url + '\'' +
                    ", appId='" + appId + '\'' +
                    ", appKey='" + appKey + '\'' +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }
    }
}