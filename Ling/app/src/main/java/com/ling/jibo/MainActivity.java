package com.ling.jibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ling.jibonetposa.RetrofitManager;
import com.ling.jibonetposa.entities.NLUResult;
import com.ling.jibonetposa.tools.IRetrofitHttp;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String baseUrl="http://60.205.170.27:9001/";
    private Button mBtnGet;
    private Button mBtnPost;

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
    }

    private void testGetModel(){
        String text="你好";
        RetrofitManager retrofitManager = new RetrofitManager();
        retrofitManager.NLURetrofitNetGet(text, new IRetrofitHttp<NLUResult>() {

            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()){
                    NLUResult result =  response.body();
                    String answer = result.getAnswer();
                    Log.d("1111","testPost"+answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable t) {

            }
        });

    }

    private void testPostModel(){
        String text="你好";
        RetrofitManager retrofitManager = new RetrofitManager();
        retrofitManager.NLURetrofitNetPost(text, new IRetrofitHttp<NLUResult>() {

            @Override
            public void onResponse(Call<NLUResult> call, Response<NLUResult> response) {
                if (response.isSuccessful()){
                    NLUResult result =  response.body();
                    String answer = result.getAnswer();
                    Log.d("1111","testPost"+answer);
                }
            }

            @Override
            public void onFailure(Call<NLUResult> call, Throwable t) {

            }
        });

    }
}
