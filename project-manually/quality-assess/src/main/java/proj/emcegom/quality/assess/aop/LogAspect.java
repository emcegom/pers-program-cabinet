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
import proj.emcegom.quality.assess.utils.JacksonUtil;
import proj.emcegom.quality.assess.utils.LogUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

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
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            Object[] args = pjp.getArgs();
            String methodName = method.getName();
            Log logAnnotation = method.getAnnotation(Log.class);
            String traceId = UUID.randomUUID().toString();
            log.info(LogUtil.template(traceId, methodName, logAnnotation.businessType(), logAnnotation.title(), JacksonUtil.toJSONString(args)));
            log.info(LogUtil.template(traceId, methodName, cost, JacksonUtil.toJSONString(result)));
        } catch (Exception e) {
            log.error("handleLog error : {}", e.getMessage());
        }
    }

    private void handleException(ProceedingJoinPoint pjp, Throwable e) {

    }
}
