package com.example.demo.aspect;


import org.aspectj.lang.reflect.MethodSignature;

/**
 * Controller日志切面基类
 * <p>
 * 用户至少需要实现allPublicMethodCall()方法，指定相应 @Pointcut
 *
 * @author andongwang
 * @version 1.0 2016/11/23
 */
public abstract class LogAspect {

    /**
     * 默认日志格式
     */
    private static final String DEFAULT_API_CALL_INFO_TEMPLATE = "SITE: {} finished in {} ms, args: [{}]";

    private static final String DEFAULT_ILLEGAL_DETAILS_REQUEST_EXCEPTION_TEMPLATE = "SITE: exception occurred in {}. {}.";

    /**
     * 默认常用切面：PUBLIC METHOD CALL，实现类需要指定 @Pointcut
     */
    protected abstract void allPublicMethodCall();

    /**
     * 返回api调用日志模版，更改模版样式需要 @Override 此方法
     */
    protected String getInternalApiCallInfoTemplate() {
        return DEFAULT_API_CALL_INFO_TEMPLATE;
    }

    /**
     * 返回错误日志模版，更改模版样式需要 @Override 此方法
     */
    protected String getIllegalDetailsRequestExceptionTemplate() {
        return DEFAULT_ILLEGAL_DETAILS_REQUEST_EXCEPTION_TEMPLATE;
    }

    protected String formatApiArgs(Object[] args, MethodSignature methodSignature) {
        String[] parameters = methodSignature.getParameterNames();
        if (parameters == null) {
            return "";
        }

        StringBuilder apiArgs = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            String parameter = parameters[i];
            Object arg = args[i];
            String argString = arg == null ? "" : arg.toString();
            apiArgs.append(parameter).append(" = ").append(argString);
            if (i < parameters.length - 1) {
                apiArgs.append(", ");
            }
        }
        return apiArgs.toString();
    }



}
