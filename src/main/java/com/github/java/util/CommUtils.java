package com.github.java.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Auther： zsm
 * Date： 2019/8/9 10:39
 */
// ctrl+Shift+T
public class CommUtils {

    private static final Gson GSON = new GsonBuilder().create();

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        InputStream in = CommUtils.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
        return properties;
    }

    /**
     * 将任意对象序列化为 json 字符串
     * @param obj
     * @return
     */
    public static  String object2Json(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * 将任意 json 字符串反序列化为任意对象
     * @param jsonStr
     * @param objClass
     * @return
     */
    public static Object json2Object(String jsonStr, Class objClass) {
        return GSON.toJson(jsonStr,objClass);
    }
}
