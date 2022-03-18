package com.jpaspring.common;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeCheckerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggerAdvice.class);

    @Around("execution(* com.jpaspring.controller.BoardController*.*(..))")
    public Object timeLog(ProceedingJoinPoint proceedingJoinPoint) {
        long start = System.currentTimeMillis();

        logger.info("Target: " + proceedingJoinPoint.getTarget());

        Object result = null;

        try {
            result= proceedingJoinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("ELAPSED TIME: " + (end - start)+ " ms");

        return result;
    }
}
