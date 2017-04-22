package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class PhantomTokenEntity extends BaseEntity {
    private String access_token; //:"f63724cf4560984033d4403f086d5af11eeba6b05a678ba6c3ed3d79864e1b5c"
    private String token_type; //:"bearer"
    private String refresh_token; //:"7c2f1117a062263a863f26ae5b7a404bab75504c04338755772a7da233830a5e"
    private String scope; //:"read_user"
    private int expires_in; //:2592000
    private int created_at; //:1480581454

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
    public String toString() {
        return "PhantomTokenEntity{" +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
