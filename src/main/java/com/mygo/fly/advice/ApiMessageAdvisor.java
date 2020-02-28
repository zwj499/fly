package com.mygo.fly.advice;

import com.mygo.fly.base.exception.FlyBusinessException;
import com.mygo.fly.base.response.JsonResponse;
import com.mygo.fly.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Order(Integer.MAX_VALUE)
public class ApiMessageAdvisor {
    private static final Log logger = LogFactory.getLog(ApiMessageAdvisor.class);

    @Around("execution(public * com.mygo.fly..*..*Controller.*(..))")
    public Object invokeAPI(ProceedingJoinPoint pjp) {
        String apiName = pjp.getSignature().getName();
        Class<?> returnClazz = ((MethodSignature) pjp.getSignature()).getReturnType();

        Object returnValue = null;
        try {
            Object[] args = pjp.getArgs();
            if (logger.isInfoEnabled()) {
                if (args != null && args.length > 0) {
                    for (Object o : args) {
                        if (!(o instanceof HttpServletRequest) && !(o instanceof HttpServletResponse) && !(o instanceof MultipartFile)) {
                            logger.info("@@" + apiName + " started,request:" + JsonUtil.toJson(o));
                        }
                    }

                }
            }

            // call to controller
            returnValue = pjp.proceed();

            this.handleSuccess(returnValue);

        } catch (FlyBusinessException e) {
            returnValue = this.handleBusinessError(e, apiName, returnClazz);
        } catch (Throwable e) {
            returnValue = this.handleSystemError(e, apiName, returnClazz);
        }

        if (logger.isInfoEnabled()) {
            logger.info("@@" + apiName + " done,response:" + JsonUtil.toJson(returnValue));
        }

        return returnValue;
    }

    private Object handleBusinessError(FlyBusinessException e, String apiName, Class<?> returnClazz) {
        logger.error("@Meet error when do " + apiName + "[" + e.getErrorCode() + "]:" + e.getMessage(), e);
        Object returnValue = null;
        try {
            returnValue = returnClazz.newInstance();
            if (returnValue instanceof JsonResponse) {
                JsonResponse response = (JsonResponse) returnValue;
                response.setCode(e.getErrorCode());
                response.setMessage(e.getMessage());
            }
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
        return returnValue;
    }

    private Object handleSystemError(Throwable e, String apiName, Class<?> returnClazz) {
        logger.error("@Meet unkonw error when do " + apiName + ":" + e.getMessage(), e);
        Object returnValue = null;
        try {
            returnValue = returnClazz.newInstance();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
        if (returnValue instanceof FlyBusinessException) {
            FlyBusinessException response = (FlyBusinessException) returnValue;
            response.setErrorCode(-1);
        }
        return returnValue;
    }

    private void handleSuccess(Object returnValue) {
        if (returnValue instanceof JsonResponse) {
            // set request ID to response
            JsonResponse response = (JsonResponse) returnValue;
            if (StringUtils.isEmpty(response.getCode())) {
                response.setCode(JsonResponse.SUCCESS_CODE);
                response.setMessage("success");
            }
        }
    }
}