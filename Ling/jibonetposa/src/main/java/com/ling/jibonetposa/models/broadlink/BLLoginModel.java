package com.ling.jibonetposa.models.broadlink;

import android.os.AsyncTask;

import java.io.File;

import cn.com.broadlink.sdk.BLLet;
import cn.com.broadlink.sdk.param.account.BLRegistParam;
import cn.com.broadlink.sdk.result.account.BLBaseResult;
import cn.com.broadlink.sdk.result.account.BLLoginResult;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class BLLoginModel {

    public interface BLTaskListener {

        void onPreExecute();

        void onPostExecute(Object result);
    }

    private BLTaskListener mLoginListener;
    private BLTaskListener mThirdAuthListener;
    private BLTaskListener mRegistListener;
    private BLTaskListener mGetVCodeListener;

    public void doLogin(String userName, String password, BLTaskListener blTaskListener) {
        mLoginListener = blTaskListener;
        new LoginTask().execute(userName, password);
    }

    public void doThirdAuth(String thirdId, BLTaskListener blTaskListener) {
        mThirdAuthListener = blTaskListener;
        new ThirdAuthTask().execute(thirdId);
    }

    public void doRegist(String countryCode, String phone, String vCode, String password, String nickName, BLTaskListener blTaskListener) {
        mRegistListener = blTaskListener;
        new RegistTask().execute(countryCode, phone, vCode, password, nickName);
    }

    public void doGetVCode(String countryCode, String phone, BLTaskListener blTaskListener) {
        mGetVCodeListener = blTaskListener;
        new GetVCodeTask().execute(countryCode, phone);
    }

    //登录
    private class LoginTask extends AsyncTask<String, Void, BLLoginResult> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mLoginListener != null) mLoginListener.onPreExecute();
        }

        @Override
        protected BLLoginResult doInBackground(String... params) {
            return BLLet.Account.login(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(BLLoginResult loginResult) {
            super.onPostExecute(loginResult);

            if (mLoginListener != null) mLoginListener.onPostExecute(loginResult);
        }
    }

    //第三方授权登录
    private class ThirdAuthTask extends AsyncTask<String, Void, BLLoginResult> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mThirdAuthListener != null) mThirdAuthListener.onPreExecute();
        }

        @Override
        protected BLLoginResult doInBackground(String... params) {
            return BLLet.Account.thirdAuth(params[0]);
        }

        @Override
        protected void onPostExecute(BLLoginResult loginResult) {
            super.onPostExecute(loginResult);
            if (mThirdAuthListener != null) mThirdAuthListener.onPostExecute(loginResult);
        }
    }

    //获取验证码
    private class RegistTask extends AsyncTask<String, Void, BLLoginResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mRegistListener != null) mRegistListener.onPreExecute();
        }

        @Override
        protected BLLoginResult doInBackground(String... params) {
            BLRegistParam registParam = new BLRegistParam();
            registParam.setCountrycode(params[0]);
            registParam.setPhoneOrEmail(params[1]);
            registParam.setCode(params[2]);
            registParam.setPassword(params[3]);
            registParam.setNickname(params[4]);
            /**图标地址*/
            registParam.setIconpath("http://musicdata.baidu.com/data2/pic/115360807/115360807.jpg");
            File iconFile = null;
            /**本地图标使用**/
//            iconFile = new File("本地图标路径");
            return BLLet.Account.regist(registParam, iconFile);
        }

        @Override
        protected void onPostExecute(BLLoginResult loginResult) {
            super.onPostExecute(loginResult);
            if (mRegistListener != null) mRegistListener.onPostExecute(loginResult);
        }
    }

    //获取验证码
    private class GetVCodeTask extends AsyncTask<String, Void, BLBaseResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mGetVCodeListener != null) mGetVCodeListener.onPreExecute();
        }

        @Override
        protected BLBaseResult doInBackground(String... params) {
            return BLLet.Account.sendRegVCode(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(BLBaseResult baseResult) {
            super.onPostExecute(baseResult);
            if (mGetVCodeListener != null) mGetVCodeListener.onPostExecute(baseResult);
        }
    }
}
