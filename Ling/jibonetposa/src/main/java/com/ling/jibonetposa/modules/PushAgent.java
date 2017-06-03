package com.ling.jibonetposa.modules;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.ling.jibonetposa.LingManager;
import com.ling.jibonetposa.R;
import com.ling.jibonetposa.third.push.ExampleUtil;
import com.ling.jibonetposa.third.push.OnPushLisenter;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by mhz小志 on 2017/6/1.
 */

public class PushAgent {

    private static final String TAG = "PushAgent";

    private Context mContext;

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

    private OnPushLisenter mPushLisenter;

    public PushAgent(Context context) {
        mContext = context;
    }

    public OnPushLisenter getPushLisenter() {
        return mPushLisenter;
    }

    public void setOnPushLisenter(OnPushLisenter pushLisenter) {
        mPushLisenter = pushLisenter;
    }

    public void initPush() {
        JPushInterface.init(mContext);
    }

    /**
     * 停止推送服务。
     * 调用了本 API 后，JPush 推送服务完全被停止。具体表现为：
     * 收不到推送消息
     * 极光推送所有的其他 API 调用都无效,不能通过 JPushInterface.init 恢复，需要调用resumePush恢复。
     */
    public void stopPush() {
        JPushInterface.stopPush(mContext);
    }

    /**
     * 恢复推送服务。
     * 调用了此 API 后，极光推送完全恢复正常工作。
     */
    public void resumePush() {
        JPushInterface.resumePush(mContext);
    }

    /**
     * 推送通知到客户端时，由 JPush SDK 展现通知到通知栏上。
     * 此 API 提供清除通知的功能，包括：清除所有 JPush 展现的通知（不包括非 JPush SDK 展现的）；清除指定某个通知。
     */
    public void clearAllNotifications() {
        JPushInterface.clearAllNotifications(mContext);
    }

    /**
     * 推送通知到客户端时，由 JPush SDK 展现通知到通知栏上。
     * 此 API 提供清除通知的功能，包括：清除所有 JPush 展现的通知（不包括非 JPush SDK 展现的）；清除指定某个通知。
     */
    public void clearNotificationById(int notificationId) {
        JPushInterface.clearNotificationById(mContext, notificationId);
    }

    /**
     * 通过极光推送，推送了很多通知到客户端时，如果用户不去处理，就会有很多保留在那里。
     * 新版本 SDK (v1.3.0) 增加此功能，限制保留的通知条数。默认为保留最近 5 条通知。
     * 开发者可通过调用此 API 来定义为不同的数量。
     *
     * @param maxNum 最多显示的条数
     */
    public void setLatestNotificationNumber(int maxNum) {
        JPushInterface.setLatestNotificationNumber(mContext, maxNum);
    }

    /**
     * @return RegistrationID
     * 集成了 JPush SDK 的应用程序在第一次成功注册到 JPush 服务器时，JPush 服务器会给客户端返回一个唯一的该设备的标识 - RegistrationID。JPush SDK 会以广播的形式发送 RegistrationID 到应用程序。
     * 应用程序可以把此 RegistrationID 保存以自己的应用服务器上，然后就可以根据 RegistrationID 来向设备推送消息或者通知。
     * <p>
     * 附加说明
     * <p>
     * 通过 RegistrationID 进行点对点推送
     * <p>
     * 可以通过 RegistrationID 来推送消息和通知， 参考文档 Push API v2， 当 receiver_type = 5 并且设置 receiver_value 为 RegistrationID 时候即可根据 RegistrationID 推送。
     */
    public String getRegistrationId() {
        return JPushInterface.getRegistrationID(mContext);
    }

    /**
     * 为安装了应用程序的用户，打上标签。其目的主要是方便开发者根据标签，来批量下发 Push 消息。
     * 可为每个用户打多个标签。
     * 举例： game, old_page, women
     *
     * @param tag null 此次调用不设置此值。（注：不是指的字符串"null"）
     *            空数组或列表表示取消之前的设置。
     *            每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
     *            有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
     *            限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，但总长度不得超过7K字节。（判断长度需采用UTF-8编码）
     */
    public int setTag(String tag) {
        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            return 1;  // tag不能为空
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
                // 格式不对
                return 2;
            }
            tagSet.add(sTagItme);
        }

        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));
        return 0;
    }

    /**
     * 为安装了应用程序的用户，取个别名来标识。以后给该用户 Push 消息时，就可以用此别名来指定。
     * 每个用户只能指定一个别名。
     * 同一个应用程序内，对不同的用户，建议取不同的别名。这样，尽可能根据别名来唯一确定用户。
     * 系统不限定一个别名只能指定一个用户。如果一个别名被指定到了多个用户，当给指定这个别名发消息时，服务器端API会同时给这多个用户发送消息。
     * 举例：在一个用户要登录的游戏中，可能设置别名为 userid。游戏运营时，发现该用户 3 天没有玩游戏了，则根据 userid 调用服务器端API发通知到客户端提醒用户。
     *
     * @param alias null 此次调用不设置此值。（注：不是指的字符串"null"）
     *              "" （空字符串）表示取消之前的设置。
     *              每次调用设置有效的别名，覆盖之前的设置。
     *              有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
     *              限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
     */
    public int setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
            // alias不能为空
            return 1;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            // 格式不对
            return 2;
        }
        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        return 0;
    }

    /**
     * 设置允许推送时间
     *
     * @param days     0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）
     *                 Sdk1.2.9 – 新功能:set的值为null,则任何时间都可以收到消息和通知，set的size为0，则表示任何时间都收不到消息和通知.
     * @param startime 允许推送的开始时间 （24小时制：startHour的范围为0到23）
     * @param endtime  允许推送的结束时间 （24小时制：endHour的范围为0到23）
     */
    public void setPushTime(Set<Integer> days, int startime, int endtime) {
        JPushInterface.setPushTime(mContext, days, startime, endtime);
    }

    /**
     * 设置通知静默时间
     *
     * @param startHour   静音时段的开始时间 - 小时 （24小时制，范围：0~23 ）
     * @param startMinute 静音时段的开始时间 - 分钟（范围：0~59 ）
     * @param endHour     静音时段的结束时间 - 小时 （24小时制，范围：0~23 ）
     * @param endMinute   静音时段的结束时间 - 分钟（范围：0~59 ）
     */
    public void setSilenceTime(int startHour, int startMinute, int endHour, int endMinute) {
        JPushInterface.setSilenceTime(mContext, startHour, startMinute, endHour, endMinute);
    }

    /**
     * 在 Android 6.0 及以上的系统上，需要去请求一些用到的权限，JPush SDK 用到的一些需要请求如下权限，因为需要这些权限使统计更加精准，功能更加丰富，建议开发者调用。
     * "android.permission.READ_PHONE_STATE"
     * "android.permission.WRITE_EXTERNAL_STORAGE"
     * "android.permission.READ_EXTERNAL_STORAGE"
     * "android.permission.ACCESS_FINE_LOCATION"
     */
    public void requestPermission() {
        JPushInterface.requestPermission(mContext);
    }

    /**
     * 设置通知提示方式 - 基础属性
     */
    public void setStyleBasic() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(mContext);
        builder.statusBarDrawable = R.drawable.ic_push;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
        Toast.makeText(mContext, "Basic Builder - 1", Toast.LENGTH_SHORT).show();
    }


    /**
     * 设置通知栏样式 - 定义通知栏Layout
     */
    public void setStyleCustom() {
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(mContext, R.layout.customer_notitfication_layout, R.id.iv_icon, R.id.tv_title, R.id.tv_text);
        builder.layoutIconDrawable = R.drawable.ic_push;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder);
        Toast.makeText(mContext, "Custom Builder - 2", Toast.LENGTH_SHORT).show();
    }


    public void setAddActionsStyle() {
        MultiActionsNotificationBuilder builder = new MultiActionsNotificationBuilder(mContext);
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "first", "my_extra1");
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "second", "my_extra2");
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "third", "my_extra3");
        JPushInterface.setPushNotificationBuilder(10, builder);

        Toast.makeText(mContext, "AddActions Builder - 10", Toast.LENGTH_SHORT).show();
    }


    /**
     * callback
     * 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
     */
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
                    if (ExampleUtil.isConnected(mContext)) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
            }

        }

    };

    /**
     * callback
     * 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
     */
    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
                    if (ExampleUtil.isConnected(mContext)) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        LingManager.getInstance().getLingLog().LOGD(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    LingManager.getInstance().getLingLog().LOGD(TAG, logs);
            }

            ExampleUtil.showToast(logs, mContext);
        }

    };

    /**
     * Method - setAliasAndTags (with Callback)
     * <p>
     * 调用此 API 来同时设置别名与标签。
     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
     * 在之前调用过后，如果需要再次改变别名与标签，只需要重新调用此 API 即可。
     * <p>
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    LingManager.getInstance().getLingLog().LOGD(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(mContext, (String) msg.obj, null, mAliasCallback);
                    break;

                case MSG_SET_TAGS:
                    LingManager.getInstance().getLingLog().LOGD(TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(mContext, null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    LingManager.getInstance().getLingLog().LOGD(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
}
