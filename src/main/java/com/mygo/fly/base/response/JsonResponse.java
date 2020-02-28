package com.mygo.fly.base.response;

/**
 * @author zwj * @since 1.0
 */
public class JsonResponse<T> {

    public static final Integer SUCCESS_CODE = 0;
    public static final Integer ERROR_CODE = 1;

    public static final String SUCCESS_MESSAGE = "查询成功";
    public static final String ERROR_MESSAGE = "查询失败";

    private Integer code;
    private String message;
    private T data;

    public JsonResponse() {
    }

    public JsonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static JsonResponse success() {
        return new JsonResponse<>(JsonResponse.SUCCESS_CODE, JsonResponse.SUCCESS_MESSAGE);
    };

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse<>(JsonResponse.SUCCESS_CODE, JsonResponse.SUCCESS_MESSAGE, data);
    };

    public static JsonResponse error(String message) {
        return new JsonResponse<>(JsonResponse.ERROR_CODE, message);
    };
}
