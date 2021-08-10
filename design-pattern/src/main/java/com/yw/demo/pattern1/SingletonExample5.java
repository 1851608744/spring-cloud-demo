package com.yw.demo.pattern1;

/**
 * @author yangwei
 * @description 懒汉模式（双重锁同步锁单例模式）
 *              单例实例在第一次使用的时候进行创建，这个类是线程安全的
 * @data 2021/07/29
 **/
public class SingletonExample5 {
    private SingletonExample5(){}

    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
