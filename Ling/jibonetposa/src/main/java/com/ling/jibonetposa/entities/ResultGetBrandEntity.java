package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultGetBrandEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private List<BrandBean> data;

    @Override
    public String toString() {
        return "ResultGetTokenEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

    public List<BrandBean> getData() {
        return data;
    }

    public void setData(List<BrandBean> data) {
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
