package com.yw.demo.common.utils;

import com.yw.demo.common.enums.ResponseCode;
import com.yw.demo.common.exception.BusinessException;
import com.yw.demo.common.exception.ParamException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangwei
 * @description 参数效验
 * @data 2021/07/23
 **/
public class ParamsUtils {
    private static ThreadLocal<String> param = new ThreadLocal<>();


    public static void isParamsNotNull(Object obj, String... argsName) {
        List<String> args = Arrays.asList(argsName);
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsNotNull(args, f, obj, argsName);
            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsNotNull(obj, obj.getClass().getSuperclass(), argsName);
            }
        } catch (Exception e) {
            String errorParam = param.get();
            param.remove();
            throw new ParamException(ResponseCode.FAILED, "参数：{} 不能为空", errorParam);
        }
    }
    
    public static boolean isParamsNotNull(Object obj, Class<?> clazz, String... strName) {
        List<String> list = Arrays.asList(strName);
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsNotNull(list, f, obj, strName);
            }
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsNotNull(obj, obj.getClass().getSuperclass(), strName);
            }
            return true;
        } catch (IllegalAccessException e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
    }

    private static void checkParamsNotNull(List<String> args,
                                           Field f, Object obj, String... argsName) throws IllegalAccessException {
        if (args.contains(f.getName())) {
            if (f.get(obj) == null || "".equals(f.get(obj))) {
                param.set(f.getName());
                throw new BusinessException(ResponseCode.PARAMS_ERROR);
            }
            //如果参数是List 遍历List中的参数
            if (f.getType().equals(List.class)) {
                for (Object o : (List) f.get(obj)) {
                    isParamsNotNull(o, argsName);
                }
            }
        }
    }

}
