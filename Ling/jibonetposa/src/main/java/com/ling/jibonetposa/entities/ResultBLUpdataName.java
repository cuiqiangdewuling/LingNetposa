package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/5/20.
 */

public class ResultBLUpdataName extends BaseEntity {
//    成功： {"errcode":0,"msg":"ok","accesskey":"xxx"}
//    失败：{“errcode”:-xx,”msg”:”xxx”}

    private int errcode;
    private String msg;
    private String accesskey;


    @Override
    public String toString() {
        return "ResultBLUpdataName{" +
                "errcode=" + errcode +
                ", msg='" + msg + '\'' +
                ", accesskey='" + accesskey + '\'' +
                '}';
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }
}
