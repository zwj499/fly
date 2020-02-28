package com.mygo.fly.base.exception;

/**
 * @author zwj * @since 1.0
 */
public class FlyBusinessException extends RuntimeException {

    private Integer errorCode;

    public FlyBusinessException() {
    }

    public FlyBusinessException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FlyBusinessException(String message) {
        super(message);
    }

    public FlyBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlyBusinessException(Throwable cause) {
        super(cause);
    }

    public FlyBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
