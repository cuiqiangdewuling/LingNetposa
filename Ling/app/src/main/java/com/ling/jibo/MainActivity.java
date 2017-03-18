package com.ling.jibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ling.jibonetposa.RetrofitManager;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.entities.NLUResult;
import com.ling.jibonetposa.models.iot.IOTAgent;
import com.ling.jibonetposa.models.testnlu.TNLUModel;
import com.ling.jibonetposa.tools.INLUGetRequest;
import com.ling.jibonetposa.tools.INLUPostRequest;
import com.ling.jibonetposa.tools.IRequestCallback;
import com.ling.jibonetposa.tools.IRetrofitHttp;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private Button mBtnGet;
    private Button mBtnPost;
    private Button mBtnGetUrl;
    private Button mBtnPostUrl;
    private Button mBtnMHZ;
    private String baseUrl = "http://60.205.170.27:9001/";

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
        mBtnGetUrl = (Button) findViewById(R.id.btn_get_url);
        mBtnPostUrl = (Button) findViewById(R.id.btn_post_url);
        mBtnMHZ = (Button) findViewById(R.id.btn_model_test);
    }

    private void initData() {
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGet();
            }
        });
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPost();
            }
        });
        mBtnGetUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGetUrl();
            }
        });
        mBtnPostUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPostUrl();
            }
        });
        mBtnMHZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonPost();
            }
        });
    }

    public void jsonPost() {
        new TNLUModel(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d("IOTAgent", "entity  " + entity.toString());
                } else {
                    Log.d("IOTAgent", "errorCode  " + errorCode);
                }
            }
        }).executeResult();
    }

    public void jsonIOTPost() {
        String code = "af0202145df6f34129adda4853da9797b9ccda1a3e7b3bc3122990692b431d77";
        AuthorizedEntity authorizedEntity = new AuthorizedEntity();
        authorizedEntity.setAuthorizedCode(code);
        new IOTAgent().doAuthorized(authorizedEntity, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d("IOTAgent", "entity  " + entity.toString());
                } else {
                    Log.d("IOTAgent", "errorCode  " + errorCode);
                }
            }
        });
    }

    /**
     * NLURetrofitGet(Map<String,String> params)
     * 该示例为get请求
     * 只需要传递map形式的参数，url，entities以及接口类封装在依赖库中
     */
    private void testGet() {
        RetrofitManager retrofitManager = new RetrofitManager();
        Map<String, String> params = new HashMap<>();
        params.put("userid", "xxxxx");
        params.put("words", "你好");
        params.put("score", "0.99");
        retrofitManager.NLURetrofitGet(params);
        retrofitManager.setRetrofitHttp(new IRetrofitHttp<NLUResult>() {

            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()) {
                    NLUResult result = response.body();
                    String answer = result.getAnswer();
                    Log.d("1111", "testGet" + answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable t) {
                Log.d("1111", "t" + t);
            }
        });
    }

    /**
     * retrofit（String url）
     * 该示例为get请求
     * 需要传递url，并写出相关entities以及接口类
     */
    private void testGetUrl() {
        RetrofitManager retrofitManager = new RetrofitManager();
        Map<String, String> params = new HashMap<>();
        params.put("userid", "xxxxx");
        params.put("words", "你好");
        params.put("score", "0.99");
        Retrofit retrofit = retrofitManager.retrofit(baseUrl);
        INLUGetRequest netRequest = retrofit.create(INLUGetRequest.class);
        Call<NLUResult> call = netRequest.getCallBack(params);
        call.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()) {
                    NLUResult result = response.body();
                    String answer = result.getAnswer();
                    Log.d("1111", "testGetUrl" + answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {

            }
        });
    }

    /**
     * retrofit（String url）
     * 该示例为post请求
     * 需要传递url，并写出相关entities以及接口类
     */
    private void testPostUrl() {
        RetrofitManager retrofitManager = new RetrofitManager();
        Map<String, String> params = new HashMap<>();
        params.put("userid", "xxxxx");
        params.put("words", "你好");
        params.put("score", "0.99");
        Retrofit retrofit = retrofitManager.retrofit(baseUrl);
        INLUPostRequest netRequest = retrofit.create(INLUPostRequest.class);

        Call<NLUResult> call = netRequest.postCallBack(params);

        call.enqueue(new Callback<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()) {
                    NLUResult result = response.body();
                    String answer = result.getAnswer();
                    Log.d("1111", "testPostUrl" + answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable throwable) {

            }
        });
    }

    /**
     * NLURetrofitGet(Map<String,String> params)
     * 该示例为post请求
     * 只需要传递map形式的参数，url，entities以及接口类封装在依赖库中
     */
    private void testPost() {
        RetrofitManager retrofitManager = new RetrofitManager();
        Map<String, String> params = new HashMap<>();
        params.put("userid", "xxxxx");
        params.put("words", "你好");
        params.put("score", "0.99");
        retrofitManager.NLURetrofitPost(params);
        retrofitManager.setRetrofitHttp(new IRetrofitHttp<NLUResult>() {
            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()) {
                    NLUResult result = response.body();
                    String answer = result.getAnswer();
                    Log.d("1111", "testPost" + answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable t) {
                Log.d("1111", "t" + t);
            }
        });
    }
}
