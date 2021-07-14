package com.yw.demo.spring;

import com.yw.demo.spring.dto.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @description
 * @data 2021/07/03
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //beanDefinition.setBeanClass(User.class);
        //applicationContext.registerBeanDefinition("user", beanDefinition);
        //applicationContext.refresh();
        Map map = new HashMap(15);
        //map.put("a", "1");
        //map.put("b", "2");

        //获取HashMap整个类
        Class<?> mapType = map.getClass();
        System.out.println(mapType);
        //获取指定属性，也可以调用getDeclaredFields()方法获取属性数组
        Field threshold = mapType.getDeclaredField("threshold");
        System.out.println(threshold);
        //将目标属性设置为可以访问
        threshold.setAccessible(true);
        //获取指定方法，因为HashMap没有容量这个属性
        Method capacity = mapType.getMethod("capacity");
        //设置目标方法可访问
        capacity.setAccessible(true);
        System.out.println("容量：" + capacity.invoke(map) + " 阈值：" + threshold.get(map) + " 元素数量：" + map.size());
        for (int i = 0; i < 17; i++) {
            map.put(i, i);
            System.out.println("容量：" + capacity.invoke(map) + " 阈值：" + threshold.get(map) + " 元素数量：" + map.size());
        }

    }
}
