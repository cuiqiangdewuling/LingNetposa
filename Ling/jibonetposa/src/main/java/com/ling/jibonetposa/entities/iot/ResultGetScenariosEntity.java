package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.bean.ScenariosBean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultGetScenariosEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private String userid;
    private List<ScenariosBean> data;

    @Override
    public String toString() {
        return "ResultGetScenariosEntity{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", userid='" + userid + '\'' +
                ", data=" + data +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<ScenariosBean> getData() {
        return data;
    }

    public void setData(List<ScenariosBean> data) {
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
