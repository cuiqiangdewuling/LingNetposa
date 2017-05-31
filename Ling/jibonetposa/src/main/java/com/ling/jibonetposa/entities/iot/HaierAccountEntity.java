package com.ling.jibonetposa.entities.iot;

import com.haier.uhome.account.model.UacDevice;
import com.haier.uhome.usdk.api.uSDKDevice;
import com.ling.jibonetposa.base.BaseEntity;

import org.json.JSONArray;

import java.util.Arrays;

/**
 * Created by mahuaizhi on 17-4-7.
 */

public class HaierAccountEntity extends BaseEntity{

    private String userName;
    private String password;
    private String userId;
    private String session;
    private JSONArray devicesJsonArray;

    private UacDevice[] devicesOfAccount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public JSONArray getDevicesJsonArray() {
        return devicesJsonArray;
    }

    public void setDevicesJsonArray(JSONArray devicesJsonArray) {
        this.devicesJsonArray = devicesJsonArray;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 判断智能设备是否属于此用户
    public boolean isSmartDeviceBelongAccount(uSDKDevice smartDeviceItem) {
        String smartDeviceId = smartDeviceItem.getDeviceId();

        UacDevice[] deviceItems = getDevicesOfAccount();
        if (deviceItems != null) {
            for (int i = 0; i < deviceItems.length; i++) {
                UacDevice deviceItem = null;
                deviceItem = deviceItems[i];
                String deviceId = deviceItem.getId();
                if (smartDeviceId.equals(deviceId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLogin() {
        if (getSession() == null) {
            return false;
        } else {
            return true;
        }
    }

    public UacDevice[] getDevicesOfAccount() {
        return devicesOfAccount;
    }

    public void setDevicesOfAccount(UacDevice[] devicesOfAccount) {
        this.devicesOfAccount = devicesOfAccount;
    }

    @Override
    public String toString() {
        return "HaierAccountEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", session='" + session + '\'' +
                ", devicesJsonArray=" + devicesJsonArray +
                ", devicesOfAccount=" + Arrays.toString(devicesOfAccount) +
                '}';
    }
}
