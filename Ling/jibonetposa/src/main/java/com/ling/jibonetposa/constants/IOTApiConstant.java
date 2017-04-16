package com.ling.jibonetposa.constants;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTApiConstant {

    //幻腾授权ApiPath
    public static final String API_PATH_PHANTON = "https://huantengsmart.com/";
    public static final String API_PATH_PHANTON_DEV = "http://huantengsmart.com:80/";
    public static final String API_PATH_PHANTON_AUTHORIZE = "https://huantengsmart.com/oauth2/authorize";
    public static final String API_PATH_PHANTON_TOKEN = "/oauth2/token";
    public static final String API_PATH_PHANTON_GET_DEV_ALL = "/api/devices.json";   // 获取当前用户的所有设备

    // Ling服务ApiPath
    // public static final String API_PATH_JIBO = "http://101.201.144.210:8082/";
    public static final String API_PATH_JIBO = "http://101.201.72.216:8082";
    public static final String API_PATH_JIBO_TOKEN_SAVE = "/jibo/token/save";
    public static final String API_PATH_JIBO_TOKEN_GET = "/jibo/token/get";
    public static final String API_PATH_JIBO_TOKEN_DELETE = "/jibo/token/delete";

    // 幻腾授权相关参数
    public static final String PHANTON_APP_ID = "f871bd790d58a4b4cf6544ff0f97722136571390d4aaf4e8b17acc9d46d842c2";
    public static final String PHANTON_APP_SECRET = "a031c82fb029b6e4b4d689a3641bc9543f63b8d9f10ee5c1260a9bdf86a66ae3";
    public static final String PHANTON_REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    public static final String PHANTON_SCOPE = "read_user write_user monitor_user read_router writer_router monitor_router read_pixel_pro write_pixel_pro monitor_pixel_pro read_generic_module write_generic_module monitor_generic_module read_wall_switch write_wall_switch monitor_wall_switch read_door_sensor write_door_sensor monitor_door_sensor read_bulb write_bulb monitor_bulb read_switch write_switch monitor_switch apply_scenario write_scenario";
    public static final String USER_ID_JIBO = "jibo";
    public static final String USER_ID_TEST = "12345678";

}
