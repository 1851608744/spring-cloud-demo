package com.yw.demo;

import lombok.Data;

/**
 * @author yangwei
 * @data 2021/06/10
 **/
@Data
public class Calc {

    private String name;
    private int age;

    public Calc(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        //calc 是指针存放在栈中
        Calc calc = new Calc("小明",1);

        //对象，存放在堆中
        new Calc("小明", 2);

        //Calc类存放在方法区中

        //对象的实例保存到堆上  对象的元数据保存到方法区上  对象的引用保存到栈上

    }

}
