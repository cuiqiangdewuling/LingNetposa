package com.ling.jibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.NLUEntity;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.models.test.NLUModelCacheGet;
import com.ling.jibonetposa.models.test.NLUModelGet;
import com.ling.jibonetposa.models.test.NLUModelPost;
import com.ling.jibonetposa.models.test.TNLUModel;

public class MainActivity extends Activity {

    private final static String TAG = "http";
    private String baseUrl = "http://60.205.170.27:9001/";
    private Button mBtnGet;
    private Button mBtnPost;
    private Button mBtnMHZ;
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

}
