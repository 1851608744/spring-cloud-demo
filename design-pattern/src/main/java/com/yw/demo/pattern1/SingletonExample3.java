package com.yw.demo.pattern1;

/**
 * @author yangwei
 * @description 懒汉模式，单例实例在第一次使用的时候进行创建，这个类是线程安全的，但是这个写法不推荐
 * @data 2021/07/29
 **/
public class SingletonExample3 {
    private SingletonExample3(){}

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
