package com.yw.demo.pattern;

/**
 * @author yangwei
 * @description
 * @data 2021/07/28
 **/
public class LiabilityChain {

    public void runChain() {
        Task task3 = new TaskImpl0();
        Task task2 = new TaskImpl1(task3);
        Task task1 = new TaskImpl2(task2);
        task1.run();
    }
}
