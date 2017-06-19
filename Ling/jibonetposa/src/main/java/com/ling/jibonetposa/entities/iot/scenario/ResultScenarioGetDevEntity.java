package com.ling.jibonetposa.entities.iot.scenario;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/4/17.
 */
public class ResultScenarioGetDevEntity extends BaseEntity {

    private int errno;
    private String errmsg;
    private Data data;


    @Override
    public String toString() {
        return "ResultGetScenariosEntity{" +
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

    public static class Data {

    }

}
