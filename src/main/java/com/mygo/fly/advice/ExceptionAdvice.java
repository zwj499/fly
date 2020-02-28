package com.mygo.fly.advice;

import com.mygo.fly.base.exception.FlyBusinessException;
import com.mygo.fly.base.response.JsonResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数", e);
        return setResponse(-1,"required_parameter_is_not_present");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return setResponse(-1,"could_not_read_parameter");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        String message = getMessage(result);
        return setResponse(-1,message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JsonResponse handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        String message = getMessage(e);
        return setResponse(-1,message);
    }

    private String getMessage(BindingResult result){
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        return String.format("%s:%s", field, code);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResponse handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();

        JsonResponse response =  new JsonResponse();
        response.setCode(-1);
        response.setMessage(("parameter:" + message));
        return response;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public JsonResponse handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return setResponse(-1,"validation_exception");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return setResponse(-1,"request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return setResponse(-1,"content_type_not_supported");
    }

    private JsonResponse setResponse(Integer code, String message){
        JsonResponse response =  new JsonResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public JsonResponse handleException(Exception e) {
        logger.error("通用异常", e);

        JsonResponse response = new JsonResponse();
        if (e instanceof UnauthorizedException) {
            // 验证权限失败
            response.setCode(2);
            response.setMessage("权限验证失败");
        } else if (e instanceof UnauthenticatedException) {
            // 用户登陆验证失败
            response.setCode(3);
            response.setMessage("登陆失败");
        } else if (e instanceof AuthorizationException) {
            // 未知的验证失败
            response.setCode(4);
            response.setMessage("未知验证失败");
        } else if (e instanceof FlyBusinessException) {
            FlyBusinessException exception = (FlyBusinessException) e;
            response.setCode(exception.getErrorCode());
            response.setMessage(exception.getMessage());
        } else {
            // 未知异常
            response.setCode(1000);
            response.setMessage("位置异常");
        }
        return response;

    }
}
