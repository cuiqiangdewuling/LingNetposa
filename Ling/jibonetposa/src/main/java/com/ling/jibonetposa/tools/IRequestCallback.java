package com.ling.jibonetposa.tools;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public interface IRequestCallback<T extends BaseEntity> {

    void responsedCallback(T entity, int errorCode, Throwable error);
}
