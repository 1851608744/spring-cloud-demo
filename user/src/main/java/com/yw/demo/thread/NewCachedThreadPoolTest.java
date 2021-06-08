package com.yw.demo.thread;


import com.yw.demo.common.utils.DateTimeUtil;

import java.util.concurrent.*;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
public class NewCachedThreadPoolTest {

    // 自定义线程池方法
    //public ThreadPoolExecutor(int corePoolSize,
    //                          int maximumpoolSize,
    //                          long keepAliceTime,
    //                          BlockingQueue<Runnable> workQueue,
    //                          ThreadFactory threadFactory,
    //                          RejectedExecutionHandler handler) {
    //
    //}

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行，执行时间" + DateTimeUtil.getCurrDateTimeStr());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                1, 100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);

        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(6));
        //for (int i = 0; i < 10; i++) {
        //    final int index = i;
        //    poolExecutor.execute(() -> {
        //        System.out.println(index + "被执行，线程名：" + Thread.currentThread().getName());
        //        try {
        //            Thread.sleep(1000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    });
        //}


    }



}
