package com.ling.jibonetposa.models.iot.brand.haier;

import com.google.gson.Gson;
import com.ling.jibonetposa.base.BaseRequestModel;
import com.ling.jibonetposa.entities.iot.ResultHaierLogin;
import com.ling.jibonetposa.iretrofit.IRequestCallback;
import com.ling.jibonetposa.iretrofit.iot.IHaierLoginPost;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;

import static com.ling.jibonetposa.constants.APIConstant.API_PATH_HAIER;

/**
 * Created by mhz小志 on 2017/7/10.
 */

public class HaierLoginModel extends BaseRequestModel<ResultHaierLogin> {

    public HaierLoginModel(IRequestCallback requestCallback) {
        super(requestCallback);
        this.mApiPath = API_PATH_HAIER;
    }

    public void login(String username, String password) {
        mParams.put("loginId", username);
        mParams.put("password", password);

        IHaierLoginPost iiotGetToken = retrofit().create(IHaierLoginPost.class);
        Call<ResultHaierLogin> token = iiotGetToken.execute(getHeaders(), organizeJsonParams());
        execute(token);

    }

    //        final String username = "13021090723";
//        final String password = "simwq123";
//
    private Map<String, String> getHeaders() {
        String bodys = new Gson().toJson(this.mParams);
        // Unix时间戳，精确到毫秒。
        String timestamp = System.currentTimeMillis() + "";
        String appId = "MB-JIBOAPPTEST-0000";
        String appKey = "6633b37f0afe0bfc6cea400af3e687be";
        // 应用版本号
        String appVersion = "0.0.1";
        // 客户端Id
        String clientId = "1234567890ASDFGHJK1234QWER1234FG";
        // sequenceId 报文流水，建议使用日期+顺序编号的方式。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        String sequenceId = simpleDateFormat.format(new Date()) + "000009";
        // 签名
        String sign = this.getSignCode(appId, appKey, timestamp, bodys);
        HashMap headers = new HashMap();
        headers.put("accessToken", "");
        headers.put("Content-type", "application/json");
        headers.put("timezone", "+8");
        headers.put("language", "zh-cn");
        headers.put("appId", appId);
        headers.put("appVersion", appVersion);
        headers.put("clientId", clientId);
        headers.put("timestamp", timestamp);
        headers.put("sign", sign);
        headers.put("sequenceId", sequenceId);
        return headers;
    }

    private String getSignCode(String appId, String appKey, String timestamp, String bodys) {
        appKey = appKey.trim().replaceAll("\"", "");
        if (bodys != null) {
            bodys = bodys.trim();
        }

        if (!bodys.equals("")) {
            bodys = bodys.replaceAll(" ", "");
            bodys = bodys.replaceAll("\t", "");
            bodys = bodys.replaceAll("\r", "");
            bodys = bodys.replaceAll("\n", "");
        }

        StringBuffer var5 = new StringBuffer();
        var5.append(bodys).append(appId).append(appKey).append(timestamp);
        MessageDigest messageDigest = null;
        byte[] bytes = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            bytes = messageDigest.digest(var5.toString().getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.byteToString(bytes);
    }

    private String byteToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String var3 = "0123456789abcdef";

        for (int i = 0; i < bytes.length; ++i) {
            builder.append(String.valueOf(var3.charAt((bytes[i] & 240) >> 4)));
            builder.append(String.valueOf(var3.charAt(bytes[i] & 15)));
        }
        return builder.toString();
    }
}
