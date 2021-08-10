package com.yw.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @data 2021/06/15
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //System.out.println("MyBeanFactoryPostProcessor 开始执行 ");
        //String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        //for (String name : beanDefinitionNames) {
        //    if ("user".equals(name)) {
        //        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
        //        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        //        propertyValues.add("name", "Jack");
        //    }
        //}
    }
}
