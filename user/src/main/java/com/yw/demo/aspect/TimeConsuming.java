package com.yw.demo.aspect;

import java.lang.annotation.*;

/**
 * @author yangwei
 * @data 2021/06/07
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeConsuming {

}
