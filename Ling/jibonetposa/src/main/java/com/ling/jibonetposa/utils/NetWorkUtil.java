package com.ling.jibonetposa.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mhz小志 on 2017/3/18.
 */

public class NetWorkUtil {

    public static String organizeParams(Map<String, Object> params) {
        Iterator<String> iterator = params.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = params.get(key);
            sb.append(key).append("=");
            if (value instanceof String) {
                String tempValue = "";
                try {
                    tempValue = URLEncoder.encode((String) value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(tempValue);
            } else {
                sb.append(value);
            }
            if (index < params.keySet().size() - 1) {
                sb.append("&");
            }
            index++;
        }
        return sb.toString();
    }
}
