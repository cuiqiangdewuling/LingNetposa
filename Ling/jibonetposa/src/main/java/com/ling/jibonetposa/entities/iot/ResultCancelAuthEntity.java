package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class ResultCancelAuthEntity extends BaseEntity {

    private int errno;
    private String errmsg;

    @Override
    public String toString() {
        return "ResultGetTokenEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}
