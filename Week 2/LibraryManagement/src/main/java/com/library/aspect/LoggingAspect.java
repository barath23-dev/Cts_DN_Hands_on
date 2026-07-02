package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.library.service.BookService.getBooks(..))")
    public void logBefore() {
        LOGGER.info("AOP Log: Intercepted before method execution starts.");
    }

    @After("execution(* com.library.service.BookService.getBooks(..))")
    public void logAfter() {
        LOGGER.info("AOP Log: Intercepted after method execution finishes.");
    }

    @Around("execution(* com.library.service.BookService.getBooks(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info("AOP Log: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
