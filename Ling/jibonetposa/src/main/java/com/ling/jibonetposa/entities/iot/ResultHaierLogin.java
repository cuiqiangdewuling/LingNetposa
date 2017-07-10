package com.ling.jibonetposa.entities.iot;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/7/10.
 */

public class ResultHaierLogin extends BaseEntity {
    private String retCode;
    private String retInfo;

    @Override
    public String toString() {
        return "ResultHaierLogin{" +
                "retCode='" + retCode + '\'' +
                ", retInfo='" + retInfo + '\'' +
                '}';
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
}
