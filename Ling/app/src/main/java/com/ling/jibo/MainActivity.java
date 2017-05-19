package com.ling.jibo;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ling.jibo.test.NLUEntity;
import com.ling.jibo.test.NLUModelCacheGet;
import com.ling.jibo.test.NLUModelGet;
import com.ling.jibo.test.NLUModelPost;
import com.ling.jibo.test.TNLUModel;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity {

    private final static String TAG = "http";
    private String baseUrl = "http://60.205.170.27:9001/";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initData();
    }


    private void initData() {
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGetModel();
            }
        });
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPostModel();
            }
        });
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testUploadPic();
            }
        });

        findViewById(R.id.btn_location_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LocationActivity.class));
            }
        });
        findViewById(R.id.btn_model_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IOTTestActivity.class));
            }
        });
        findViewById(R.id.btn_lication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ShowLocation.class));
//                testShowLocation();
                testShowLocation1();
            }
        });
        findViewById(R.id.btn_opengps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGPS();
//                openGPS(mContext);
//                openGPSSettings();
                setLocationMode(mContext, 3);
            }
        });
        findViewById(R.id.btn_checkpro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "权限状态：" + areLocationPermissionsGranted(mContext), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_showpro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPhotoPermissions();
            }
        });
        findViewById(R.id.btn_getroot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upgradeRootPermission(getPackageCodePath());

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

    Handler mHandler = new Handler() {


    };

    private void testShowLocation1() {

        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        LingManager.getInstance().getLingLog().LOGD("Criteria 1");
        Criteria criteria = new Criteria();
        LingManager.getInstance().getLingLog().LOGD("Criteria 2");
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        LingManager.getInstance().getLingLog().LOGD("Criteria 3" + provider);
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
        LingManager.getInstance().getLingLog().LOGD("Criteria 4");

        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 100 * 1000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MainActivity.this, "onProviderEnabled", Toast.LENGTH_SHORT).show();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                getLocatonDesc(latitude, longitude);
                Toast.makeText(MainActivity.this, latitude + "　／　" + longitude, Toast.LENGTH_SHORT).show();
                LingManager.getInstance().getLingLog().LOGD("123456", latitude + "　／　" + longitude);
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
        Log.i(TAG, "address = " + address.toString());
        String adminArea = address.getAdminArea();
        Log.i(TAG, "adminArea = " + adminArea);
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

    /**
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @return 应用程序是/否获取Root权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * 监听GPS
     */
    private void initGPS() {
        LocationManager locationManager = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            // 转到手机设置界面，用户设置GPS
            Intent intent = new Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
//            Toast.makeText(mContext, "请打开GPS",
//                    Toast.LENGTH_SHORT).show();
//            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
//            dialog.setMessage("请打开GPS");
//            dialog.setPositiveButton("确定",
//                    new android.content.DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1) {
//
//                            // 转到手机设置界面，用户设置GPS
//                            Intent intent = new Intent(
//                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
//
//                        }
//                    });
//            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    arg0.dismiss();
//                }
//            });
//            dialog.show();
        } else {
            // 弹出Toast
            Toast.makeText(this, "GPS is ready",
                    Toast.LENGTH_LONG).show();
            // 弹出对话框
            new AlertDialog.Builder(this).setMessage("GPS is ready")
                    .setPositiveButton("OK", null).show();
        }
    }

    public static boolean areLocationPermissionsGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        permission += ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPhotoPermissions() {
//        requestPermissions(
//                new String[]{
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                },
//                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0) {
            int result = 0;
            for (int val : grantResults) {
                result += val;
            }
            if (result == PackageManager.PERMISSION_GRANTED) {
                switch (requestCode) {
                    case 1:
                        break;
                }
            }
        } else {

        }
    }

    private void toggleGPS() {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(this, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }


    private void openGPSSettings() {
        //获取GPS现在的状态（打开或是关闭状态）
        boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER);

        if (gpsEnabled) {

            //关闭GPS
            Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, false);
        } else {
            //打开GPS  www.2cto.com
            Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);

        }
    }

    /**
     * mode can be one of:
     * android.provider.Settings.Secure.LOCATION_MODE_HIGH_ACCURACY
     * android.provider.Settings.Secure.LOCATION_MODE_OFF
     * android.provider.Settings.Secure.LOCATION_MODE_SENSORS_ONLY;
     * android.provider.Settings.Secure.LOCATION_MODE_BATTERY_SAVING
     *
     * @param context
     * @param mode
     */
    public static void setLocationMode(Context context, int mode) {
        mode = android.provider.Settings.Secure.LOCATION_MODE_HIGH_ACCURACY;
        Intent intent = new Intent("com.android.settings.location.MODE_CHANGING");
        int currentMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF);
        intent.putExtra("CURRENT_MODE", currentMode);
        intent.putExtra("NEW_MODE", mode);

        Log.e("jerry", "currentMode=" + currentMode + " newmode=" + mode);

        try {
            context.sendBroadcast(intent, android.Manifest.permission.WRITE_SECURE_SETTINGS);
            Settings.Secure.putInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, mode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
