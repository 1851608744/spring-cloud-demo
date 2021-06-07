package com.yw.demo.aspect;


import java.lang.annotation.*;

/**
 * @author Hunter
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogController {

    // 具体操作
    String description();
    // 日志级别
    int logLevel() default 0;
    // 日志进程/方法名
    String method();
}
