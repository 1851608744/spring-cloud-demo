package com.yw.demo.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yangwei
 * @data 2021/06/18
 **/
public class LockTest {

    int num = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    //版本号实现
    //AtomicStampedReference
    public void increase() {
        //synchronized (this) {
        //    num++;
        //}
        atomicInteger.incrementAndGet();
        //确保oldValue一致（处理value被其他线程修改的情况）
        while (true) {
            //incrementAndGet实现原理
            int oldValue = atomicInteger.get();
            int newValue = oldValue + 1;
            //对比oldValue的值是否一致，true 把newValue赋予内部的value变量 （CAS实现）
            if (atomicInteger.compareAndSet(oldValue, newValue)) {
                break;
            }

        }
    }

    public long getNum() {
        //return num;
        return atomicInteger.get();
    }


    public static void main(String[] args) throws InterruptedException {
        LockTest a = new LockTest();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                a.increase();
            }
        });

        t1.start();

        for (int i = 0; i < 10000000; i++) {
            a.increase();
        }
        t1.join();

        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));

        System.out.println(a.getNum());
    }

}
