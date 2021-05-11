package ru.kpfu.itis.transportprojectimpl.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheAspect.class);

    public Map<String, Object> cache;

    public CacheAspect() {
        cache = new HashMap<String, Object>();
    }


    @Pointcut("execution(@Cacheable * *.*(..))")
    @SuppressWarnings("unused")
    private void cache() {
    }

    @Around("cache()")
    public Object aroundCachedMethods(ProceedingJoinPoint thisJoinPoint)
            throws Throwable {
        log.debug("Execution of Cacheable method");

        StringBuilder keyBuff = new StringBuilder();

        keyBuff.append(thisJoinPoint.getTarget().getClass().getName());

        keyBuff.append(".").append(thisJoinPoint.getSignature().getName());

        keyBuff.append("(");
        for (final Object arg : thisJoinPoint.getArgs()) {
            keyBuff.append(arg.getClass().getSimpleName() + "=" + arg + ";");
        }
        keyBuff.append(")");
        String key = keyBuff.toString();

        log.debug("Key = " + key);
        Object result = cache.get(key);
        if (result == null) {
            log.debug("Result not yet cached.");
            result = thisJoinPoint.proceed();
            log.info("Storing value '" + result + "' to cache");
            cache.put(key, result);
        } else {
            log.info("Result '" + result + "' was found in cache");
        }

        return result;
    }

}
