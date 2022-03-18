package com.jpaspring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggerAdvice.class);

    @Before("execution(* com.jpaspring.controller.BoardController*.*(..))")
    public void startLog(JoinPoint joinPoint) {
        logger.info("Aop StartLog : ================="+joinPoint.getSignature().getName()+"====================");
    }

    @After("execution(* com.jpaspring.controller.BoardController*.*(..))")
    public void endLog(JoinPoint joinPoint) {
        logger.info("Aop StartLog : ================="+joinPoint.getSignature().getName()+"====================");
    }

    @AfterThrowing(pointcut = "execution(* com.jpaspring.controller.BoardController.*(..))" , throwing = "e")
    public void logControllerException(JoinPoint joinPoint, Exception e) {
        logger.info("Aop logException : ================="+joinPoint.getSignature().getName()+"====================");
        logger.info("Aop logException : ================="+e.getMessage()+"====================");
    }


    @AfterThrowing(pointcut = "execution(* com.jpaspring.service.BoardService.*(..))" , throwing = "e")
    public void logServiceException(JoinPoint joinPoint, Exception e) {
        logger.info("Aop logException : ================="+joinPoint.getSignature().getName()+"====================");
        logger.info("Aop logException : ================="+e.getMessage()+"====================");
    }

}
