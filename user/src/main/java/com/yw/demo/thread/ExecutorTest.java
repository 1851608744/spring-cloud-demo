package com.yw.demo.thread;


import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
public class ExecutorTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行，执行时间" + new Date() + "执行线程" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);


    }

}
