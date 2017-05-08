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
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.ling.jibonetposa.base.BaseRequestModel.TAG;

/**
 * Created by mhz小志 on 2017/5/8.
 */

public class LocationAgent {

    private Context mContext;
    private ILocationLisenter mILocationLisenter;

    /**
     * 获取当前地理位置，返回经纬度与所在城市名称
     */
    public void getLocation(Context context, ILocationLisenter locationLisenter) {
        this.mILocationLisenter = locationLisenter;
        this.mContext = context;
        this.testShowLocation();
    }

    public interface ILocationLisenter {

        void onSuccess(double latitude, double longitude, String city);

        void onFailure(int code);
    }

    public void saveJiboLocation(String userid, double latitude, double longitude, String city, IRequestCallback iRequestCallback) {

    }

    private void testShowLocation() {
        LocationManager locationManager;
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 3 * 1000, 500, mLocationListener);
    }

    /**
     * 判断是否有可用的内容提供器
     *
     * @return 不存在返回null
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(mContext, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null && location.getLatitude() != 0 && location.getLongitude() != 0) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Toast.makeText(mContext, latitude + "　／　" + longitude, Toast.LENGTH_SHORT).show();
                getLocatonDesc(latitude, longitude);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void getLocatonDesc(double latitude, double longitude) {
        Geocoder gc = new Geocoder(mContext, Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address = locationList.get(0);//得到Address实例
        //Log.i(TAG, "address =" + address);
        String countryName = address.getCountryName();//得到国家名称，比如：中国
        Log.i(TAG, "countryName = " + countryName);
        String locality = address.getLocality();//得到城市名称，比如：北京市
        Log.i(TAG, "locality = " + locality);
        for (int i = 0; address.getAddressLine(i) != null; i++) {
            String addressLine = address.getAddressLine(i);//得到周边信息，包括街道等，i=0，得到街道名称
            Log.i(TAG, "addressLine = " + addressLine);
        }
    }
}
