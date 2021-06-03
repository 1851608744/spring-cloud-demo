package com.yw.demo.aspect;


import java.lang.annotation.*;

/**
 * 自定义注解demo
 * @author Hunter
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectDemn {

    String value() default "first one";

}
