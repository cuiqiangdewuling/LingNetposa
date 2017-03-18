package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class AuthorizedEntity extends BaseEntity {

    private String userId;
    private String authorizedCode;

    @Override
    public String toString() {
        return "AuthorizedEntity{" +
                "userId='" + userId + '\'' +
                ", authorizedCode='" + authorizedCode + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorizedCode() {
        return authorizedCode;
    }

    public void setAuthorizedCode(String authorizedCode) {
        this.authorizedCode = authorizedCode;
    }
}
