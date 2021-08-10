package com.yw.demo.spring;

import com.yw.demo.spring.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangwei
 * @data 2021/06/15
 **/
public class ContextDemo {
    public static void main(String[] args) {
        //使用配置文件来启动一个Application
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");
        //从 context 中取出我们的Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);


        System.out.println(messageService.getMessage());

    }
}
