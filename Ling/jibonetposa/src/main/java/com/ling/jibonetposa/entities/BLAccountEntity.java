package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mahuaizhi on 17-4-7.
 */

public class BLAccountEntity extends BaseEntity {

    private String userName;
    private String password;

    public BLAccountEntity() {
    }

    public BLAccountEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "BLAccountEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
