package com.ling.jibonetposa.constants;

/**
 * Created by cuiqiang on 2017/3/22.
 */

public class BaseHttpConstant {

    //离线时缓存保存4周,单位:秒
    public static final int MAXSTALE = 60 * 60 * 24 * 28;

    //在线缓存在1分钟内可读取 单位:秒
    public static final int MAXAGE  = 1 * 60;

    //设置缓存大小 10M
    public static final int CACHESIZE = 10 * 1024 * 1024;

    //带cache的连接超时时间
    public static final int CACHECONNECTTIMEOUT=15;

    //外部存储
    public static final String EXTERNAL ="external";

    //内部存储
    public static final String INTERNAL = "internal";
}
