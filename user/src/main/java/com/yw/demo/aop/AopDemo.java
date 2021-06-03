package com.yw.demo.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author yangwei
 * @data 2021/06/03
 **/
@Log4j2
@Aspect
@Component
public class AopDemo {


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
        //处理完请求，返回内容
        log.info("RESPONSE: " + ret);
    }

    /**
     * 前置通知
     * 通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等
     * 通过RequestContextHolder来获取请求信息，Session信息
     * @param joinPoint
     */
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint) {
        log.info("前置通知");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用到最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        log.info("代理的是哪一个方法" + signature.getName());
        //AOP代理类的名字
        log.info("AOP代理类的名字" + signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        //获取请求参数
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> parameterMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter, request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if (obj.length > 0) {
            log.info("请求的参数信息为：" + str);
        }
    }

    /**
     * 后置返回通知 在某连接点之后执行的通知，通常在一个匹配的方法返回的时候执行（可以在后置通知中绑定返回值）
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数*       returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     *       对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "webLog()", returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        log.info("第一个后置返回通知的返回值："+keys);
    }

    @AfterReturning(value = "webLog()",returning = "keys",argNames = "keys")
    public void doAfterReturningAdvice2(String keys) {
        log.info("第二个后置返回通知的返回值：" + keys);
    }


    /**
     * 后置最终通知 （目标方法只要执行完了就会执行后置通知方法）
     * @param joinPoint
     */
    @After(value = "webLog()")
    public void doAfterAdvice(JoinPoint joinPoint) {
        log.info("后置最终通知执行！！！！");
    }

    /**
     * 环绕通知
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "webLog()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * 有时候我们定义切面的时候，切面中需要使用到目标对象的某个参数，
     * 如何使切面能得到目标对象的参数呢？可以使用args来绑定。
     * 如果在一个args表达式中应该使用类型名字的地方使用一个参数名字，
     *  那么当通知执行的时候对象的参数值将会被传递进来。
     * @param id
     */
    @Before("execution(* findById*(..)) &&" + "args(id,..)")
    public void twiceAsOld1(Long id){
        System.err.println ("切面before执行了。。。。id==" + id);

    }

}
