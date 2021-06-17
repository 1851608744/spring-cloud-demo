package com.yw.demo;

/**
 * @author yangwei
 * @data 2021/06/10
 **/
public class ThreadDemo extends Thread{

    private String name;

    static int tickets = 100;

    static Object obj = new Object();

    public ThreadDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tickets > 0) {
                    System.out.println(name + "卖出座位是" + tickets-- + "号");
                }else {
                    break;
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(name + "卖票结束");
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo("窗口1");
        ThreadDemo threadDemo1 = new ThreadDemo("窗口2");
        ThreadDemo threadDemo2 = new ThreadDemo("窗口3");
        threadDemo.start();
        threadDemo1.start();
        threadDemo2.start();

    }

}
