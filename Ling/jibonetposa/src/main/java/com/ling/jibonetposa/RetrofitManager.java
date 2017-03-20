package com.ling.jibonetposa;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.ling.jibonetposa.iretrofit.test.IImageRequestPost;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



/**
 * Created by cuiqiang on 2017/3/16.
 */

public class RetrofitManager {

    public Retrofit retrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 上传图片
     * @param baseUrl
     * @param context
     */
    public void uploadpic(String baseUrl, Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IImageRequestPost imageRequestPost = retrofit.create(IImageRequestPost.class);

        String filename = "上传图片";

        Resources resources =context.getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.ic_launcher) + "/");
        File file = new File(String.valueOf(uri));
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        Call<String> call = imageRequestPost.uploadImage(filename,requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("1111", response.message());
                Log.v("1111", "success");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("1111", t.toString());
            }

        });

    }

    public void uploadManyPic(String baseUrl, ArrayList<String> paths, String desp){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IImageRequestPost imageRequestPost = retrofit.create(IImageRequestPost.class);
        Map<String,RequestBody> photos = new HashMap<>();
        if (paths.size()>0) {
            for (int i=0;i<paths.size();i++) {
                photos.put("photos\"; filename=\"icon.png",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
            }
        }
        Call<String> stringCall = imageRequestPost.uploadImage(desp, photos.get("photos\"; filename=\"icon.png"));
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("1111", "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("1111", "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
            }
        });

    }

}
