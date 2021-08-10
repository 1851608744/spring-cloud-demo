package com.yw.demo.pattern;

/**
 * @author yangwei
 * @description
 * @data 2021/07/28
 **/
public class TaskImpl0 implements Task{

    private Task task;

    public TaskImpl0() {

    }

    public TaskImpl0(Task task) {
        this.task = task;
    }


    @Override
    public void run() {
        System.out.println("taskImpl0 is run !!!");
        System.out.println(task);
        if (task != null) {
            task.run();
        }
    }
}
