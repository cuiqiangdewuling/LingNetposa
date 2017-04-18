package com.ling.jibonetposa.models.broadlink;

import cn.com.broadlink.sdk.BLLet;
import cn.com.broadlink.sdk.interfaces.controller.BLDeviceScanListener;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class BLDevicesModel {

    public void startProbe() {
        BLLet.Controller.startProbe();
    }


    public void stopProbe() {
        BLLet.Controller.stopProbe();
    }

    public void setOnDeviceScanListener(BLDeviceScanListener scanListener) {
        BLLet.Controller.setOnDeviceScanListener(scanListener);
    }
}