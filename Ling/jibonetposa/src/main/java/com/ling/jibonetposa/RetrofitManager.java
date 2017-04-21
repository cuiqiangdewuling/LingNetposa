package com.ling.jibonetposa;

import android.content.Context;

import com.ling.jibonetposa.utils.NetStatusUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by cuiqiang on 2017/3/16.
 */

public class RetrofitManager {

    public Retrofit retrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public Retrofit retrofitCache(String baseUrl,Context context) {
        OkHttpClient okHttpClient = setClient(context, 0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
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
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
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

    //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
    public Interceptor getRewriteCacheControlInterceptor() {
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response originalResponse = chain.proceed(request);
                int maxAge = 1 * 60; // 在线缓存在1分钟内可读取 单位:秒
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        };
        return rewriteCacheControlInterceptor;
    }


    public OkHttpClient setClient(Context context,int code){
        File httpCacheDirectory;
        if (code ==0 ){
            //外部存储
            httpCacheDirectory = new File(context.getExternalCacheDir(), "responses");
        }else{
            //设置缓存路径 内置存储
            httpCacheDirectory = new File(context.getCacheDir(), "responses");
        }

        //设置缓存 10M
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getRewriteCacheControlInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        return client;
    }

//    /**
//     * 上传图片
//     * @param baseUrl
//     * @param context
//     */
//    public void uploadpic(String baseUrl, Context context){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        IImageRequestPost imageRequestPost = retrofit.create(IImageRequestPost.class);
//
//        String filename = "上传图片";
//
//        Resources resources =context.getResources();
//        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.ic_launcher) + "/");
//        File file = new File(String.valueOf(uri));
//        RequestBody requestBody =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        Call<String> call = imageRequestPost.uploadImage(filename,requestBody);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.v("1111", response.message());
//                Log.v("1111", "success");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("1111", t.toString());
//            }
//
//        });
//
//    }
//
//    public void uploadManyPic(String baseUrl, ArrayList<String> paths, String desp){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        IImageRequestPost imageRequestPost = retrofit.create(IImageRequestPost.class);
//        Map<String,RequestBody> photos = new HashMap<>();
//        if (paths.size()>0) {
//            for (int i=0;i<paths.size();i++) {
//                photos.put("photos\"; filename=\"icon.png",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
//            }
//        }
//        Call<String> stringCall = imageRequestPost.uploadImage(desp, photos.get("photos\"; filename=\"icon.png"));
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.d("1111", "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("1111", "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
//            }
//        });
//    }
}
