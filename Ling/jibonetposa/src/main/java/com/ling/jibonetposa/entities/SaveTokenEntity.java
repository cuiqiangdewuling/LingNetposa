package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class SaveTokenEntity extends BaseEntity {

    private String brand_id;// Phantom
    private String access_token; //:"f63724cf4560984033d4403f086d5af11eeba6b05a678ba6c3ed3d79864e1b5c",
    private String token_type; //:"bearer",
    private int expires_in; //:2592000,
    private String refresh_token; //:"7c2f1117a062263a863f26ae5b7a404bab75504c04338755772a7da233830a5e",
    private String scope; //:"read_user",
    private int created_at; //:1480581454

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveTokenEntity that = (SaveTokenEntity) o;

        if (expires_in != that.expires_in) return false;
        if (created_at != that.created_at) return false;
        if (brand_id != null ? !brand_id.equals(that.brand_id) : that.brand_id != null)
            return false;
        if (access_token != null ? !access_token.equals(that.access_token) : that.access_token != null)
            return false;
        if (token_type != null ? !token_type.equals(that.token_type) : that.token_type != null)
            return false;
        if (refresh_token != null ? !refresh_token.equals(that.refresh_token) : that.refresh_token != null)
            return false;
        return scope != null ? scope.equals(that.scope) : that.scope == null;

    }

    @Override
    public int hashCode() {
        int result = brand_id != null ? brand_id.hashCode() : 0;
        result = 31 * result + (access_token != null ? access_token.hashCode() : 0);
        result = 31 * result + (token_type != null ? token_type.hashCode() : 0);
        result = 31 * result + expires_in;
        result = 31 * result + (refresh_token != null ? refresh_token.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + created_at;
        return result;
    }

    @Override
    public String toString() {
        return "SaveTokenEntity{" +
                "brand_id='" + brand_id + '\'' +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
