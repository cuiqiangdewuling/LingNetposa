package com.ling.jibonetposa.modules;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.entities.locaiton.SaveLocationEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.location.GetCityDataModel;
import com.ling.jibonetposa.models.location.GetLocationModel;
import com.ling.jibonetposa.models.location.SaveLocationModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by mhz小志 on 2017/5/8.
 */

public class LocationAgent {

    public static final String TAG = "LocationAgent";

    private long mTimeout = 10000;// 默认超时时间

    private Context mContext;
    private String mProvider;// 位置提供方式  GPS   WIFI
    private Handler mHandler = new Handler();
    private ILocationLisenter mILocationLisenter;
    private LocationManager locationManager;

    public LocationAgent(Context context) {
        this.mContext = context;
    }

    /**
     * 将Location数据保存到Server
     *
     * @param userid           UserId
     * @param province         省
     * @param city             市
     * @param latitude         经度
     * @param longitude        纬度
     * @param iRequestCallback 回调
     */
    public void saveLocationToServer(String userid, String province, String city, double latitude, double longitude, IRequestCallback iRequestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        final String finalUserid = userid;
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + finalUserid);
        LingManager.getInstance().getLingLog().LOGD("city: " + finalUserid + "    province: " + province);
        SaveLocationModel saveLocationModel = new SaveLocationModel(iRequestCallback);
        saveLocationModel.saveLocation(new SaveLocationEntity(finalUserid, province, city, latitude + "", longitude + ""));
    }

    /**
     * 获取保存到Server的Location位置
     */
    public void getLocationFromServer(String userid, IRequestCallback iRequestCallback) {
        if (LingManager.getInstance().isUseTestUserid()) {
            userid = LingManager.getInstance().getTestUserId();
        }
        final String finalUserid = userid;
        LingManager.getInstance().getLingLog().LOGD("finalUserid: " + finalUserid);
        new GetLocationModel(iRequestCallback).getLocation(finalUserid);
    }

    /**
     * 获取城市列表
     *
     * @param iRequestCallback
     */
    public void getCityDataFromServer(IRequestCallback iRequestCallback) {
        new GetCityDataModel(iRequestCallback).getCityData();
    }

    /**
     * 获取当前地理位置，返回经纬度与所在城市名称
     */
    public void getAppLocation(ILocationLisenter locationLisenter) {
        this.mILocationLisenter = locationLisenter;
        this.showLocation();
    }

    /**
     * 设置获取手机地理位置的超时限制
     */
    public void setTimeout(long timeout) {
        this.mTimeout = timeout;
    }

    public interface ILocationLisenter {

        void onSuccess(double latitude, double longitude, String province, String city);

        void onFailure();
    }


    /**
     * 获取到LocationManager，设置好位置提供器，开始请求精确位置
     */
    private void showLocation() {
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        mProvider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        requestLocation();
    }

    /**
     * 请求获取精确位置
     */
    private void requestLocation() {
        mHandler.postDelayed(mTimeoutRunnable, mTimeout);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (mILocationLisenter != null) mILocationLisenter.onFailure();
            return;
        }
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(mProvider, 3 * 1000, 0, mLocationListener);
    }

    private Runnable mTimeoutRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(mTimeoutRunnable);
            locationManager.removeUpdates(mLocationListener);
            LingManager.getInstance().getLingLog().LOGD(TAG, "获取位置超时，当前 Provider：" + mProvider);
            List<String> prodiverlist = locationManager.getProviders(true);
            if (mProvider.equals(LocationManager.GPS_PROVIDER) && prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
                mProvider = LocationManager.NETWORK_PROVIDER;
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (mILocationLisenter != null) mILocationLisenter.onFailure();
                    return;
                }
                LingManager.getInstance().getLingLog().LOGD(TAG, "更换位置提供器，Provider：" + mProvider);
                requestLocation();
            } else {
                LingManager.getInstance().getLingLog().LOGD(TAG, "获取位置超时，当前 Provider：" + mProvider);
                if (mILocationLisenter != null) mILocationLisenter.onFailure();
            }
        }
    };

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null && location.getLatitude() != 0 && location.getLongitude() != 0) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (latitude == 0 && longitude == 0) {
                    if (mILocationLisenter != null) {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "latitude,longitude： 0");
                        if (mILocationLisenter != null) mILocationLisenter.onFailure();
                    }
                } else {
                    mHandler.removeCallbacks(mTimeoutRunnable);
                    if (mILocationLisenter != null)
                        getLocatonDesc(latitude, longitude);
                }
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (mILocationLisenter != null) mILocationLisenter.onFailure();
                    locationManager.removeUpdates(this);
                    return;
                }
                locationManager.removeUpdates(this);
            }
            LingManager.getInstance().getLingLog().LOGD(TAG, "onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            LingManager.getInstance().getLingLog().LOGD(TAG, "onProviderDisabled");
        }
    };

    /**
     * 获取到当前位置周边信息
     *
     * @param latitude
     * @param longitude
     */
    private void getLocatonDesc(double latitude, double longitude) {
        Geocoder gc = new Geocoder(mContext, Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            if (mILocationLisenter != null) mILocationLisenter.onFailure();
            e.printStackTrace();
        }
        Address address = locationList.get(0);//得到Address实例

        String adminArea = address.getAdminArea();
        LingManager.getInstance().getLingLog().LOGD(TAG, "province = " + adminArea);
        String locality = address.getLocality();//得到城市名称，比如：北京市
        LingManager.getInstance().getLingLog().LOGD(TAG, "city = " + locality);

        for (int i = 0; address.getAddressLine(i) != null; i++) {
            String addressLine = address.getAddressLine(i);//得到周边信息，包括街道等，i=0，得到街道名称
            LingManager.getInstance().getLingLog().LOGD(TAG, "addressLine = " + addressLine);
        }
        mILocationLisenter.onSuccess(latitude, longitude, adminArea, locality);
    }
}
