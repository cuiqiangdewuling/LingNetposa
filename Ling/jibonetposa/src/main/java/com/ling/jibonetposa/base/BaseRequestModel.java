package com.ling.jibonetposa.base;

import android.content.Context;

import com.google.gson.Gson;
import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.constants.BaseHttpConstant;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.utils.NetStatusUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class BaseRequestModel<T extends BaseEntity> {

    public static final String TAG = BaseRequestModel.class.getSimpleName();

    public static final int RETROFIT_SUCCESS = 0;
    public static final int RETROFIT_ERROR = 1;
    public static final int ERROR_PASS_MISTAKE = -2;//用户名或密码错误 --- 海尔错误码 B22109-22820：用户名密码错误
    public static final int RETROFIT_WRONG = -1;

    protected IRequestCallback mRequestCallback;
    protected Map<String, Object> mParams = new HashMap<>();
    protected String mApiPath;
    protected Call<T> mCall;

    public BaseRequestModel(IRequestCallback requestCallback) {
        mRequestCallback = requestCallback;
    }

    /**
     * 获取Retrofit网络请求对象
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mApiPath)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 获取Retrofit网络请求带缓存的对象
     */
    public Retrofit retrofitCache(Context context, String cacheUrl, String fileName) {
        OkHttpClient okHttpClient = setClient(context, cacheUrl, fileName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mApiPath)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 如果请求是带Json格式的请求，执行此方法将参数转换成Json
     */
    protected RequestBody organizeJsonParams() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String resultJson = gson.toJson(this.mParams);
        sb.append(resultJson);
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), sb.toString());
    }

    /**
     * 如果请求是带Json格式的请求，执行此方法将参数转换成Json
     */
    protected RequestBody organizeJsonParams(BaseEntity baseEntity) {
        String json = new Gson().toJson(baseEntity);
        LingManager.getInstance().getLingLog().LOGD("Body:   " + json);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
    }

    /**
     * 如果请求是带普通参数的请求，执行此方法返回mParams，
     * <p>
     * 以后如果有需求的话，可以在这里对Params做数据处理
     */
    protected Map<String, Object> organizeParams() {
        return mParams;
    }

    /**
     * Retrofit进行网络请求，
     *
     * @param call
     */
    protected void execute(Call<T> call) {
        mCall = call;
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "executeSuccess: " + response.toString() + " / " + response.body().toString());
                    if (BaseRequestModel.this.mRequestCallback != null)
                        BaseRequestModel.this.mRequestCallback.responsedCallback(response.body(), RETROFIT_SUCCESS, (Throwable) null);
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "executeError: " + response.toString());
                    if (BaseRequestModel.this.mRequestCallback != null)
                        BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_WRONG, new RetrofitException(response.toString()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                LingManager.getInstance().getLingLog().LOGD(TAG, "throwable: " + throwable.toString());
                if (BaseRequestModel.this.mRequestCallback != null)
                    BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_ERROR, throwable);
//                if (call.isCanceled()) {
//                    LingManager.getInstance().getLingLog().LOGD(TAG, "request is canceled");
//                } else {
//                    LingManager.getInstance().getLingLog().LOGD(TAG, "error:" + throwable.getMessage());
//                }
            }
        });
    }

    /**
     * 请求复用
     */
    public void resetExecute() {
        Call<T> newCall = mCall.clone();
        newCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "resetExecuteSuccess: " + response.toString());
                    BaseRequestModel.this.mRequestCallback.responsedCallback(response.body(), RETROFIT_SUCCESS, (Throwable) null);
                } else {
                    LingManager.getInstance().getLingLog().LOGD(TAG, "resetExecuteError: " + response.toString());
                    BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_WRONG, new RetrofitException(response.toString()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                LingManager.getInstance().getLingLog().LOGD(TAG, "resetExecuteThrowable: " + throwable.toString());
                BaseRequestModel.this.mRequestCallback.responsedCallback(null, RETROFIT_ERROR, throwable);
            }
        });
    }

    /**
     * 获取缓存
     */
    public Interceptor getInterceptor() {
        Interceptor baseInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetStatusUtil.hasNetwork()) {
                    /**
                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                     */
                    int maxStale = BaseHttpConstant.MAXSTALE; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
        return baseInterceptor;
    }


    /**
     * 只有网络拦截器环节 才会写入缓存写入缓存,在有网络的时候
     * 设置缓存时间
     */
    public Interceptor getRewriteCacheControlInterceptor() {
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response originalResponse = chain.proceed(request);
                int maxAge = BaseHttpConstant.MAXAGE; // 在线缓存在1分钟内可读取 单位:秒
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        };
        return rewriteCacheControlInterceptor;
    }

    /**
     * 设置okhttp的client
     *
     * @param context
     * @param CacheDirectory
     * @return
     */
    public OkHttpClient setClient(Context context, String CacheDirectory, String fileName) {

        File httpCacheDirectory;

        if (BaseHttpConstant.EXTERNAL.equals(CacheDirectory)) {
            //外部存储
            httpCacheDirectory = new File(context.getExternalCacheDir(), fileName);
        } else if (BaseHttpConstant.INTERNAL.equals(CacheDirectory)) {
            //设置缓存路径 内置存储
            httpCacheDirectory = new File(context.getCacheDir(), fileName);
        } else {
            File file = new File(CacheDirectory);
            httpCacheDirectory = new File(file, fileName);
        }

        //设置缓存 10M
        int cacheSize = BaseHttpConstant.CACHESIZE;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getRewriteCacheControlInterceptor())
                .connectTimeout(BaseHttpConstant.CACHECONNECTTIMEOUT, TimeUnit.SECONDS)
                .build();
        return client;
    }

    /**
     * 取消当前对象的网络请求
     */
    public void cancel() {
        if (mCall != null)
            mCall.cancel();
    }
}
