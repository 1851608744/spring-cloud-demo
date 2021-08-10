package com.yw.demo.pattern1;

/**
 * @author yangwei
 * @description 枚举方式进行实例化，是线程安全的，此种方式也是线程最安全的
 * @data 2021/07/29
 **/
public class SingletonExample7 {

    private SingletonExample7(){}

    public static SingletonExample7 getInstance() {
        //return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;


    }

}
