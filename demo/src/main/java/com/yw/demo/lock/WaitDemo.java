package com.yw.demo.lock;

import java.util.Scanner;

/**
 * @author yangwei
 * @data 2021/06/07
 **/
public class WaitDemo {
    private static Object object = new Object();

    public static class A extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            //打印完后要等待 B 线程启动，并完成某个条件
            synchronized (object) { //要等那个对象，就要先对其加锁
                try {
                    //有等待就要被唤醒
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //抛出的异常(与线程中断有关)，如果一直等的时候，用户要求它停下来，它就会收到一个异常InterruptedException
                }
            }
            for (int i = 100; i < 110; i++) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new A();
        a.start();
        //程序运行到这里一直等待的状态
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        //这个方法是要求输入
        scanner.nextLine();
        synchronized (object) {
            //调用这个方法唤醒程序(唤醒正在等待对象监视器的单个线程。 ),object指向某一个对象，会找到这个对象中的线程（其中的一个，不是所有），把线程的状态切换到RUNNABLE.但并不是马上执行，需要main线程把锁释放后才可执行。
            object.notify();
            //唤醒正在等待对象监视器的所有线程
            //object.notifyAll();
        }
    }
}
//只有输入一个字符后才会接着打印后面
