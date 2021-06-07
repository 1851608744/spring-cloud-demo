package com.yw.demo.aop;

import com.yw.demo.aspect.LogController;
import com.yw.demo.common.dto.SysUserDto;
import com.yw.demo.common.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author yangwei
 * @data 2021/06/07
 **/
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class OperaLogAspect {

    private final ApplicationEventPublisher publisher;


    @Pointcut("@annotation(com.yw.demo.aspect.LogController)")
    public void annotationPoint() {

    }

    @Pointcut("@annotation(com.yw.demo.aspect.TimeConsuming)")
    public void methodTimePoint() {

    }


    /**
     * @TimeConsuming注解实现
     */
    @Before(value = "methodTimePoint()")
    public void beforeTime() {
        log.info("************************" + DateTimeUtil.getCurrDateTimeStr() + "************************");
    }

    //@Before(value = "annotationPoint() && @annotation(logController)", argNames = "joinPoint, logController")
    //public void beforeController(JoinPoint joinPoint, LogController logController) {
    //    HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    //    HttpSession session = request.getSession();
    //    SysUserDto user = (SysUserDto) session.getAttribute("sysUserDto");
    //    String realMethodName = joinPoint.getSignature().getName();
    //    // 异步处理日志 publisher.publishEvent(new LogToDbEvent( LogToDbEventEntity.builder() .date(new Date()) .userName(user == null ? "system" : user.getUserName()) .method(logController.method()) .logLevel(logController.logLevel()) .description(logController.description()) .realMethod(realMethodName) .build())); }
    //    log.info("Aspect = [{}] ,user [{}] , method [{}] , logLevel [{}] , do [{}] , realMethod [{}]", DateTimeUtil.getCurrDateTimeStr(), user == null ? "system" : user.getName(), logController.method(), logController.logLevel(), logController.description(), realMethodName);
    //}

    //@Around(value = "methodTimePoint()")
    //public Object apiTimeConsuming(ProceedingJoinPoint pjp) throws Throwable {
    //    long begin = System.currentTimeMillis();
    //    String method = pjp.getSignature().getName();
    //    String className = pjp.getTarget().getClass().getName();
    //    Object ret = pjp.proceed();
    //    log.info("Aspect = [{}] ,class [{}] , method [{}] , time consuming[{}]", new Date(), className, method, System.currentTimeMillis() - begin);
    //    return ret;
    //}


}