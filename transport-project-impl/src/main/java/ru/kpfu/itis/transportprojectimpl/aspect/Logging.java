package ru.kpfu.itis.transportprojectimpl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logging {
    private static final Logger log = LoggerFactory.getLogger(Logging.class);

    @Pointcut("execution(public * ru.kpfu.itis.transportprojectimpl.service.*.*(..))")
    public void stringProcessingMethods() {
    }

    @Before("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();
        log.info("название метода: " + methodName);
    }

    @Around("@annotation(LogTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }
}
