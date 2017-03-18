package com.ling.jibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.models.NLUModelGet;
import com.ling.jibonetposa.models.NLUModelPost;
import com.ling.jibonetposa.models.iot.IOTAgent;
import com.ling.jibonetposa.models.testnlu.TNLUModel;
import com.ling.jibonetposa.tools.IRequestCallback;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "http";
    private Button mBtnGet;
    private Button mBtnPost;
    private Button mBtnMHZ;
    private String baseUrl = "http://60.205.170.27:9001/";
    private Button mBtnGetPic;


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
        mBtnMHZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonPost();
            }
        });
        mBtnGetPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testUploadPic();
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
        }).executeJsonResult();
    }

    public void jsonIOTPost() {
        String code = "1ae221f62b8f677e8fd607d59d2759a3a2680986834914db573bfc9b18362f70";
        AuthorizedEntity authorizedEntity = new AuthorizedEntity();
        authorizedEntity.setUserId("123456");
        authorizedEntity.setAuthorizedCode(code);
        new IOTAgent().getPhantomAuthorized(authorizedEntity, new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == BaseRequestModel.RETROFIT_SUCCESS) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        });
    }

    private void testGetModel(){
        String text="你好";
        new NLUModelGet(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        }).executedNetRequest(text);
    }


    private void testPostModel(){
        String text="你好";
        new NLUModelPost(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d(TAG, "entity  " + entity.toString());
                } else {
                    Log.d(TAG, "errorCode  " + errorCode);
                }
            }
        }).executedNetRequest(text);
    }

    private void testUploadPic(){

    }

}
