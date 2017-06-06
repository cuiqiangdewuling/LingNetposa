package com.ling.jibonetposa.constants;

/**
 * Created by mhz小志 on 2017/3/17.
 */

public class IOTApiConstant {

    // Ling服务ApiPath
    public static final String API_PATH_JIBO = "http://101.201.72.216:8082";
    public static final String API_PATH_JIBO_TEST = "http://101.201.72.216:8056";
    public static final String API_PATH_JIBO_TOKEN_SAVE = "/jibo/token/save";
    public static final String API_PATH_JIBO_TOKEN_GET = "/jibo/token/get";
    public static final String API_PATH_JIBO_TOKEN_DELETE = "/jibo/token/delete";
    public static final String API_PATH_JIBO_DEVICES = "/jibo/devices";
    public static final String API_PATH_JIBO_BRANDS = "/jibo/devices/factory";
    public static final String API_PATH_JIBO_BRANDS_CONFIGURE = "/jibo/app/configure";
    public static final String API_PATH_JIBO_SCENARIOS = "/jibo/scenarios";
    public static final String API_PATH_JIBO_INFORMATION = " /jibo/information";



    //Location Api
    public static final String API_PATH_JIBO_LOCATION = "/jibo/geo";
    public static final String API_PATH_JIBO_CITY = "/jibo/area";
    public static final String API_PATH_JIBO_CITY_DATA = "/jibo/devices/factory";


    public static final String OAUTH_REDIRECT_URI = "http://101.201.72.216:8082/test/code";

    //幻腾授权ApiPath
    public static final String API_PATH_PHANTON = "https://huantengsmart.com/";
    public static final String API_PATH_PHANTON_AUTHORIZE = "https://huantengsmart.com/oauth2/authorize";
    public static final String API_PATH_PHANTON_TOKEN = "/oauth2/token";

    // 幻腾授权相关参数
    public static final String PHANTON_APP_ID = "f871bd790d58a4b4cf6544ff0f97722136571390d4aaf4e8b17acc9d46d842c2";
    public static final String PHANTON_APP_SECRET = "a031c82fb029b6e4b4d689a3641bc9543f63b8d9f10ee5c1260a9bdf86a66ae3";
    public static final String PHANTON_SCOPE = "read_user write_user monitor_user read_router writer_router monitor_router read_pixel_pro write_pixel_pro monitor_pixel_pro read_generic_module write_generic_module monitor_generic_module read_wall_switch write_wall_switch monitor_wall_switch read_door_sensor write_door_sensor monitor_door_sensor read_bulb write_bulb monitor_bulb read_switch write_switch monitor_switch apply_scenario write_scenario";

    // 幻腾操作设备相关
    public static final String API_PATH_PHANTON_DEV = "http://huantengsmart.com:80/";
    public static final String API_PATH_PHANTON_GET_DEV_ALL = "/api/devices.json";   // 获取当前用户的所有设备
    public static final String API_PATH_PHANTON_UPDATE_NAME = "/api/devices/";   // 获取当前用户的所有设备

    //BroadLink授权相关参数
    public static final String BROADLINK_API_PATH = "https://oauthtest.ibroadlink.com/";
    public static final String BROADLINK_API_PATH_TOKEN = "/oauth/v2/token";
    public static final String BROADLINK_CLIENT_ID = "a857c79c95f5bb3903c121798accc05f";
    public static final String BROADLINK_CLIENT_SECRET = "17b49a2098c5dca76c7fede2b1278337";
    public static final String BROADLINK_LID = "00fa61bc922b61025371f83488ed4575";
    public static final String BROADLINK_LICENSE = "APphvJIrYQJTcfg0iO1FdZLds65vQxUhghUgauRDiASyy3PzyhSet3WvMQtZQuZTs4nUWAAAAAAHTXzp5cOJ2xstSmsrCVqCQ3nC07KRDxTf6OoLdox8o+EHrb3OJc5vpM3pvCQs83cNVuCt0jv3kkHMReLOissKXMLuZLzUf3626inKQwTo2AAAAAA=";

    public static final String API_PATH_BROADLINK_DEVICE = "https://00fa61bc922b61025371f83488ed4575cloudauthorize.ibroadlink.com:15334";
    public static final String API_PATH_BROADLINK_VERIFY = "/dnaproxy/ v1/accesskey/specify";


}
