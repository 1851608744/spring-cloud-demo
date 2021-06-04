package com.yw.demo.common.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author xucaigui
 * @date 2017/9/9
 */

@SuppressWarnings("rawtypes")
public class GsonUtils {
    private static Gson gson = new Gson();

    /**
     * 序列化一个自定义对象中包含多个List对象的对象
     * t为最外层
     * clazz为里层,如果里层有多个List，依次传入List对应对象的Class
     * @param obj
     * @param t
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T  fromJson(Class t, String obj, Class ... clazz) {
        if (clazz.length == 0) {
            return gson.fromJson(obj, (Type)t);
        }
        Type objectType = type(t, clazz);
        return gson.fromJson(obj, objectType);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String)obj;
        }
        return gson.toJson(obj);
    }

    public static <T> T toBean(Object data, Type t) {
        return gson.fromJson(gson.toJson(data), t);
    }

    public static <T> T toListBean(Object data, Class ... t) {
        return GsonUtils.fromJson(List.class, gson.toJson(data), t);
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

}
