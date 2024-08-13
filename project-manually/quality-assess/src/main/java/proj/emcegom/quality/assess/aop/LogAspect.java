package proj.emcegom.quality.assess.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import proj.emcegom.quality.assess.annotation.Log;
import proj.emcegom.quality.assess.enums.BusinessType;
import proj.emcegom.quality.assess.model.entity.LogEntity;
import proj.emcegom.quality.assess.utils.JacksonUtil;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(proj.emcegom.quality.assess.annotation.Log)")
    public void pt() {
    }

    @Around("pt()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            handleException(pjp, e);
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - startTime;
            handleLog(pjp, cost, result);
        }
        return result;
    }

    private void handleLog(ProceedingJoinPoint pjp, long cost, Object result) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        Object[] args = pjp.getArgs();
        log.info(LogEntity.builder()
                .method(method.getName())
                .cost(cost)
                .inputParam(args)
                .outputParam(result)
                .businessType(logAnnotation.businessType())
                .title(logAnnotation.title())
                .build().toString());
    }

    private void handleException(ProceedingJoinPoint pjp, Throwable e) {

    }
}
