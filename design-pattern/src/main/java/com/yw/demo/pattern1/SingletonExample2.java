package com.yw.demo.pattern1;

/**
 * @author yangwei
 * @description 饿汉模式，单例实例在类装载的时候进行创建，是线程安全的
 * @data 2021/07/28
 **/
public class SingletonExample2 {
    private SingletonExample2(){}

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }

}
