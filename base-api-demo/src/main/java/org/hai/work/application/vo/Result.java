package org.hai.work.application.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author mr.ahai
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "";

    /**
     * 返回代码
     */
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    private T resultData;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
    @JsonIgnore
    private String onlTable;

    public Result() {
    }

    /**
     * 兼容VUE3版token失效不跳转登录页面
     *
     * @param code
     * @param message
     */
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Deprecated
    public static Result<Object> ok() {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        return r;
    }

    @Deprecated
    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(msg);
        return r;
    }

    @Deprecated
    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setResultData(data);
        return r;
    }

    public static <T> Result<T> OK() {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        return r;
    }

    public static <T> Result<T> OK(String msg) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> OK(T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setResultData(data);
        return r;
    }

    public static <T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(msg);
        r.setResultData(data);
        return r;
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage(msg);
        r.setResultData(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(500, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noauth(String msg) {
        return error(510, msg);
    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = 200;
        this.success = true;
        return this;
    }

    public Result<T> error500(String message) {
        this.message = message;
        this.code = 500;
        this.success = false;
        return this;
    }

}