package com.PolKevich.ITBcamp0824.AOP;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@Aspect
public class LogService {

    @Pointcut("execution(* com.PolKevich.ITBcamp0824.service.*.*(..))")
    public void aroundServicePointcut() {
    }

    @Around("aroundServicePointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method {} in {} start" ,joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType());

        Object proceed = joinPoint.proceed();

        log.info("Method {} in {} finished", joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType());

        return proceed;
    }
}
