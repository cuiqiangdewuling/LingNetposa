package com.ling.jibonetposa.models.iot.broadlink;

/**
 * Created by mhz小志 on 2017/4/16.
 */

public class BLLoginModel {
//
//    private static final int MSG_LOGIN_SUCCESS = 0x1001;
//    private static final int MSG_LOGIN_ERROR = 0x1002;
//
//    public interface BLTaskListener {
//
//        void onPreExecute();
//
//        void onPostExecute(Object result);
//    }
//
//    private IRequestCallback mLoginListener;
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MSG_LOGIN_SUCCESS:
//                    if (mLoginListener != null)
//                        mLoginListener.responsedCallback((BLAccountEntity) msg.obj, RETROFIT_SUCCESS, null);
//                    break;
//                case MSG_LOGIN_ERROR:
//                    if (mLoginListener != null)
//                        mLoginListener.responsedCallback(null, RETROFIT_ERROR, new RetrofitException((String) msg.obj));
//                    break;
//            }
//        }
//    };
//
//    private BLTaskListener mThirdAuthListener;
//    private BLTaskListener mRegistListener;
//    private BLTaskListener mGetVCodeListener;
//
//    public void doLogin(final String userName, final String password, IRequestCallback requestCallback) {
//        mLoginListener = requestCallback;
//        LingManager.getInstance().getLingLog().LOGD("BL_doLogin");
//        ATask aTask = new DefaultNoNetworkTask() {
//            @Override
//            public void run() {
//                LingManager.getInstance().getLingLog().LOGD("BL_doLogin 1");
//                BLLoginResult loginResult = BLLet.Account.login(userName, password);
//                LingManager.getInstance().getLingLog().LOGD("BL_doLogin 2");
//                if (loginResult != null && loginResult.getError() == BLAccountErrCode.SUCCESS) {
//                    BLAccountEntity blAccountEntity = new BLAccountEntity(userName, password);
//                    if (mLoginListener != null)
//                        mLoginListener.responsedCallback(blAccountEntity, RETROFIT_SUCCESS, null);
//
//                    Message msg = Message.obtain();
//                    msg.obj = blAccountEntity;
//                    msg.what = MSG_LOGIN_SUCCESS;
//                    mHandler.sendMessage(msg);
//                } else {
//                    Message msg = Message.obtain();
//                    msg.obj = loginResult.getMsg();
//                    msg.what = MSG_LOGIN_ERROR;
//                    mHandler.sendMessage(msg);
//                }
//            }
//        };
//        aTask.setPiority(ATask.TASK_LEVEL_BACKGROUND);
//        ThreadPool.getInstance().addTask(aTask);
//    }
//
//    public void doThirdAuth(final String thirdId, BLTaskListener blTaskListener) {
//        mThirdAuthListener = blTaskListener;
//        new ThirdAuthTask().execute(thirdId);
//    }
//
//    public void doRegist(String countryCode, String phone, String vCode, String password, String nickName, BLTaskListener blTaskListener) {
//        mRegistListener = blTaskListener;
//        new RegistTask().execute(countryCode, phone, vCode, password, nickName);
//    }
//
//    public void doGetVCode(String countryCode, String phone, BLTaskListener blTaskListener) {
//        mGetVCodeListener = blTaskListener;
//        new GetVCodeTask().execute(countryCode, phone);
//    }
//
//    //第三方授权登录
//    private class ThirdAuthTask extends AsyncTask<String, Void, BLLoginResult> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if (mThirdAuthListener != null) mThirdAuthListener.onPreExecute();
//        }
//
//        @Override
//        protected BLLoginResult doInBackground(String... params) {
//            return BLLet.Account.thirdAuth(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(BLLoginResult loginResult) {
//            super.onPostExecute(loginResult);
//            if (mThirdAuthListener != null) mThirdAuthListener.onPostExecute(loginResult);
//        }
//    }
//
//    //获取验证码
//    private class RegistTask extends AsyncTask<String, Void, BLLoginResult> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if (mRegistListener != null) mRegistListener.onPreExecute();
//        }
//
//        @Override
//        protected BLLoginResult doInBackground(String... params) {
//            BLRegistParam registParam = new BLRegistParam();
//            registParam.setCountrycode(params[0]);
//            registParam.setPhoneOrEmail(params[1]);
//            registParam.setCode(params[2]);
//            registParam.setPassword(params[3]);
//            registParam.setNickname(params[4]);
//            /**图标地址*/
//            registParam.setIconpath("http://musicdata.baidu.com/data2/pic/115360807/115360807.jpg");
//            File iconFile = null;
//            /**本地图标使用**/
////            iconFile = new File("本地图标路径");
//            return BLLet.Account.regist(registParam, iconFile);
//        }
//
//        @Override
//        protected void onPostExecute(BLLoginResult loginResult) {
//            super.onPostExecute(loginResult);
//            if (mRegistListener != null) mRegistListener.onPostExecute(loginResult);
//        }
//    }
//
//    //获取验证码
//    private class GetVCodeTask extends AsyncTask<String, Void, BLBaseResult> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if (mGetVCodeListener != null) mGetVCodeListener.onPreExecute();
//        }
//
//        @Override
//        protected BLBaseResult doInBackground(String... params) {
//            return BLLet.Account.sendRegVCode(params[0], params[1]);
//        }
//
//        @Override
//        protected void onPostExecute(BLBaseResult baseResult) {
//            super.onPostExecute(baseResult);
//            if (mGetVCodeListener != null) mGetVCodeListener.onPostExecute(baseResult);
//        }
//    }
}
