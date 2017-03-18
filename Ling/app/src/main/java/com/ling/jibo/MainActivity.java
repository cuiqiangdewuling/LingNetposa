package com.ling.jibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ling.jibonetposa.base.BaseEntity;
import com.ling.jibonetposa.entities.AuthorizedEntity;
import com.ling.jibonetposa.models.NLUModelGet;
import com.ling.jibonetposa.models.NLUModelPost;
import com.ling.jibonetposa.models.iot.IOTAgent;
import com.ling.jibonetposa.models.testnlu.TNLUModel;
import com.ling.jibonetposa.tools.IRequestCallback;

public class MainActivity extends AppCompatActivity {


    private Button mBtnGet;
    private Button mBtnPost;
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
        mBtnMHZ = (Button) findViewById(R.id.btn_model_test);
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

    private void testGetModel(){
        String text="你好";
        new NLUModelGet(new IRequestCallback() {
            @Override
            public void responsedCallback(BaseEntity entity, int errorCode, Throwable error) {
                if (errorCode == 0) {
                    Log.d("IOTAgent", "entity  " + entity.toString());
                } else {
                    Log.d("IOTAgent", "errorCode  " + errorCode);
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
                    Log.d("IOTAgent", "entity  " + entity.toString());
                } else {
                    Log.d("IOTAgent", "errorCode  " + errorCode);
                }
            }
        }).executedNetRequest(text);
    }

}
