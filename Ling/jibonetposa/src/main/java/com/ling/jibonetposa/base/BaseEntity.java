package com.ling.jibonetposa.base;

import java.io.Serializable;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class BaseEntity implements Serializable {
    
    public static final int STATUS_OK = 1;
    public static final int STATUS_ERROR = -1;

    private static final long serialVersionUID = 1L;

    private transient int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
