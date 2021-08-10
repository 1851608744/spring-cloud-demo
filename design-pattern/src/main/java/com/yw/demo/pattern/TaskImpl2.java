package com.yw.demo.pattern;

/**
 * @author yangwei
 * @description
 * @data 2021/07/28
 **/
public class TaskImpl2 implements Task{
    private Task task;

    public TaskImpl2() {

    }

    public TaskImpl2(Task task) {
        this.task = task;
    }


    @Override
    public void run() {
        System.out.println("taskImpl2 is run !!!");
        System.out.println(task);
        if (task != null) {
            task.run();
        }
    }
}
