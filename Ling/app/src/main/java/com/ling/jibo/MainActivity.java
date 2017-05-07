package com.ling.jibo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ling.jibo.test.NLUEntity;
import com.ling.jibo.test.NLUModelCacheGet;
import com.ling.jibo.test.NLUModelGet;
import com.ling.jibo.test.NLUModelPost;
import com.ling.jibo.test.TNLUModel;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity {

    private final static String TAG = "http";
    private String baseUrl = "http://60.205.170.27:9001/";
    private Button mBtnGet;
    private Button mBtnPost;
    private Button mBtnMHZ;
    private Button mBtnGetPic;
    private Button mBtnGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mBtnGet = (Button) findViewById(R.id.btn_get);
        mBtnPost = (Button) findViewById(R.id.btn_post);
        mBtnMHZ = (Button) findViewById(R.id.btn_model_test);
        mBtnGetPic = (Button) findViewById(R.id.btn_pic);
        mBtnGetLocation = (Button) findViewById(R.id.btn_lication);
    }

    private void initData() {
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGetModel();
            }
        });
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPostModel();
            }
        });
        mBtnGetPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testUploadPic();
            }
        });
        mBtnMHZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IOTTestActivity.class));
            }
        });
        mBtnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ShowLocation.class));
//                testShowLocation();
                testShowLocation1();

            }
        });
    }

    public void jsonPost() {
        new TNLUModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        }).executeResult();
    }

    private void testGetModel() {
        String text = "你好";
        NLUModelGet nluModelGet = new NLUModelGet(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        });
        nluModelGet.executedNetRequest(text);
        // nluModelGet.cancel();
        nluModelGet.resetExecute();

    }

    private void testPostModel() {
        String text = "你好";
        NLUModelPost nluModelPost = new NLUModelPost(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        });
        nluModelPost.executedNetRequest(text);
    }

    private void testUploadPic() {
        NLUModelCacheGet nluModelCacheGet = new NLUModelCacheGet(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (entity != null) {
                    NLUEntity nluEntity = (NLUEntity) entity;
                    String answer = nluEntity.getAnswer();
                    Log.d(TAG, "answer" + answer);
                } else {
                    Log.d(TAG, "error" + error.getMessage());
                }
            }
        });
        nluModelCacheGet.executedNetRequest("你好", MainActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("123456","yey");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.d("123456","boo");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void testShowLocation1() {
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("123456", "拒绝权限");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1001);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        if (location == null) {
            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.d("123456", latitude + "　／　" + longitude);
            Toast.makeText(MainActivity.this, latitude + "　／　" + longitude, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "location null", Toast.LENGTH_SHORT).show();
        }
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MainActivity.this, "onProviderEnabled", Toast.LENGTH_SHORT).show();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                getLocatonDesc(latitude, longitude);
                Toast.makeText(MainActivity.this, latitude + "　／　" + longitude, Toast.LENGTH_SHORT).show();
                Log.d("123456", latitude + "　／　" + longitude);
            }

            @Override
            public void onProviderDisabled(String arg0) {
                Toast.makeText(MainActivity.this, "onProviderDisabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String arg0) {
                Toast.makeText(MainActivity.this, "onProviderEnabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                Toast.makeText(MainActivity.this, "onStatusChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testShowLocation() {
        LocationManager mLocationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        String provider = judgeProvider(mLocationManager);
        if (provider != null) {//有位置提供器的情况
            //为了压制警告
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "权限问题", Toast.LENGTH_SHORT).show();
                return;
            }
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 60 * 1000, 1, mLocationListener);
        }
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
            Toast.makeText(MainActivity.this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Toast.makeText(MainActivity.this, latitude + "　／　" + longitude, Toast.LENGTH_SHORT).show();
            getLocatonDesc(latitude, longitude);
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
        Geocoder gc = new Geocoder(this, Locale.getDefault());
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
