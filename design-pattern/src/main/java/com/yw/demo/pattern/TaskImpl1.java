package com.yw.demo.pattern;

/**
 * @author yangwei
 * @description
 * @data 2021/07/28
 **/
public class TaskImpl1 implements Task{

    private Task task;

    public TaskImpl1() {

    }

    public TaskImpl1(Task task) {
        this.task = task;
    }


    @Override
    public void run() {
        System.out.println("taskImpl1 is run !!!");
        System.out.println(task);
        if (task != null) {
            task.run();
        }
    }
}
