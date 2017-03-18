package com.ling.jibonetposa.models.iot;

import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.tools.IRequestCallback;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTAgent {
    public void doAuthorized(AuthorizedEntity authorizedEntity, IRequestCallback requestCallback) {
        new IOTGetTokenModel(requestCallback).executeReqTokenResult(authorizedEntity.getAuthorizedCode());
    }
}
