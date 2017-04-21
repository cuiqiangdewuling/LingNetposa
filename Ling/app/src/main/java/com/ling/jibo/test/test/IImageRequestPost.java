package com.ling.jibo.test.test;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by cuiqiang on 2017/3/18.
 */

public interface IImageRequestPost {

    /**
     * 上传一张图片
     * @param description
     * @param imgs
     * @return
     */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"")RequestBody imgs);


}
