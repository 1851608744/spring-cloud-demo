package com.yw.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @data 2021/06/09
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Test {
    public static Test test;

    public void isAlive() {
        System.out.println("I am alive :");
    }

    //@Override
    //protected void finalize() throws Throwable {
    //    super.finalize();
    //    System.out.println("finalize method executed!");
    //    test = this;
    //}

    public static void main(String[] args){

        Map map = new HashMap();
        int[] a = {1, 2};
        int b = (a == null) ? 0 : a.length;
        System.out.println(1 << 30);
        String str = "";



        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            Thread.currentThread().sleep(10000);
        //            System.out.println(Thread.currentThread().getName());
        //            System.out.println("睡了5s后打印,这是出main之外的非守护线程，这个推出后这个引用结束，jvm声明周期结束。任务管理的java/javaw.exe进程结束");
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}).start();
        //System.out.println("mian线程直接打印，mian线程结束，电脑任务管理器的java/javaw.exe进程并没有结束。");
        //


        //test = new Test();
        //test = null;
        //System.gc();
        //Thread.sleep(500);
        //if (test != null) {
        //    test.isAlive();
        //}else {
        //    System.out.println("no,I am dead :(");
        //}
        //
        //test = null;
        //System.gc();
        //Thread.sleep(500);
        //if (test != null) {
        //    test.isAlive();
        //}else {
        //    System.out.println("no,I am dead :(");
        //}
    }
}
