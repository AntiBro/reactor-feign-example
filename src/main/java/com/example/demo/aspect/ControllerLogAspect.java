package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * API Controller 调用日志记录切面
 * <p>
 * 按照以下格式输出日志：
 * INTERNAL API: {method name} {args} cost {time length} ms.
 */

@Aspect
@Component
public class ControllerLogAspect extends LogAspect {

    /**
     * 定义 SITE 调用切面
     * 切面为 com.sohu.media.api.service.service.api.controller 包中的所有 public 方法
     */
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    @Override
    protected void allPublicMethodCall() {
        // no need to implement this pointcut
    }

    /**
     * 为 API调用切面 打印默认日志
     */
    @Around("allPublicMethodCall()")
    protected Object logDefaultFeignApiCallInfo(ProceedingJoinPoint pjp) throws Throwable {

        Object apiControllerCalled = pjp.getTarget();
        StringBuilder apiName = new StringBuilder("");
        Class<?> targetClass = apiControllerCalled.getClass();
        Logger logger = LoggerFactory.getLogger(targetClass);
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        apiName.append(methodSignature.getName());
        try {
            // 开始计时
            long timeStart = System.currentTimeMillis();

            // 获取API信息
            String apiArgs = formatApiArgs(pjp.getArgs(), methodSignature);

            // proceed the api method
            Object returnValue = pjp.proceed();

            // print normal api call info logs, then return
            long timeUsed = System.currentTimeMillis() - timeStart;
            if (timeUsed >= 500) {
                logger.warn("SLOW API CALL " + getInternalApiCallInfoTemplate(), apiName.toString(), timeUsed, apiArgs);
            }
            logger.info(getInternalApiCallInfoTemplate(), apiName.toString(), timeUsed, apiArgs);
            return returnValue;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

}