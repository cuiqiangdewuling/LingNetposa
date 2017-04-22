package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class ResultGetTokenEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private Data data;

    @Override
    public String toString() {
        return "ResultGetTokenEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Data {
        private String access_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        @Override
        public String toString() {
            return "Data{" +
                    ", access_token='" + access_token + '\'' +
                    '}';
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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
