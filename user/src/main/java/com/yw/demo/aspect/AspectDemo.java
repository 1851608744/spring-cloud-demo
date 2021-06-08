package com.yw.demo.aspect;


import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解demo
 * @author Hunter
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
//只允许用于类的字段上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectDemo {

    /**
     * 合法的参数值
     * @return
     */
    String[] paramValues();

    /**
     * 提示信息
     * @return
     */
    String message() default "参数不为指定值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "first one";

}
