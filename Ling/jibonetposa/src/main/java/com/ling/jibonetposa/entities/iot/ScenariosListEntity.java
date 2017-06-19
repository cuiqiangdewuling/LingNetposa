package com.ling.jibonetposa.entities.iot;

import android.os.Parcel;
import android.os.Parcelable;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.bean.DeviceBean;

import java.util.List;

/**
 * Created by mhz小志 on 2017/6/12.
 */

public class ScenariosListEntity extends BaseEntity implements Parcelable {

    private String userId;
    private List<DeviceBean> scenarioList;
    private DeviceBean currentScenario; // 用户当前选择的场景
    private int type;

    public ScenariosListEntity() {
    }

    public ScenariosListEntity(String userId, List<DeviceBean> val, DeviceBean currentScenario) {
        this.userId = userId;
        this.scenarioList = val;
        this.currentScenario = currentScenario;
    }

    public static final Creator<ScenariosListEntity> CREATOR = new Creator<ScenariosListEntity>() {
        @Override
        public ScenariosListEntity createFromParcel(Parcel in) {
            return new ScenariosListEntity(in);
        }

        @Override
        public ScenariosListEntity[] newArray(int size) {
            return new ScenariosListEntity[size];
        }
    };

    @Override
    public String toString() {
        return "ScenariosListEntity{" +
                "userId='" + userId + '\'' +
                ", scenarioList=" + scenarioList +
                ", currentScenario=" + currentScenario +
                ", type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected ScenariosListEntity(Parcel in) {
        userId = in.readString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<DeviceBean> getScenarioList() {
        return scenarioList;
    }

    public void setScenarioList(List<DeviceBean> scenarioList) {
        this.scenarioList = scenarioList;
    }

    public DeviceBean getCurrentScenario() {
        return currentScenario;
    }

    public void setCurrentScenario(DeviceBean currentScenario) {
        this.currentScenario = currentScenario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeInt(type);
    }
}
