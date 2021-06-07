package com.yw.demo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author yangwei
 * @data 2021/06/03
 **/
@Log4j2
@Aspect
@Component
public class AopTest {


    /**
     * 定义切入点 execution(<修饰符模式>？<返回类型模式>？<方法名模式>？(<参数模式>) <异常模式>?)
     * 除了返回类型模式、方法名模式和参数模式外，其他都是可选项。
     */
    @Pointcut("execution (public * com.yw.demo..controller.*.*(..))")
    public void webLog() {

    }



    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("前置通知");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("URL: " + request.getRequestURL().toString());
        log.info("HTTP_MEtHOD: " + request.getMethod());
        log.info("IP: " + request.getRemoteAddr());
        log.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }



    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        System.out.println("后置返回通知");
        //处理完请求，返回内容
        log.info("RESPONSE: " + ret);
    }
}
