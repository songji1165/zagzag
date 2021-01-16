package com.jtrio.zagzag.aop;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.jtrio.zagzag..*Controller.*(..))")
    public void logging(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("userId: {}, requesst: {}, params: {}", securityUser.getUserId(), request.getRequestURL(), joinPoint.getArgs());
    }

    @Around("execution(* com.jtrio.zagzag..*Controller.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            log.error("error");
        }
        long end = System.currentTimeMillis();
        long time = (end - start);
        log.info("userId: {}, requesst: {}, params: {}, elapse time : {}", getUserId(), request.getRequestURL(),
                proceedingJoinPoint.getArgs(), time);
        return result;
    }

    private Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof SecurityUser ? ((SecurityUser) principal).getUserId() : -1;
    }
}
