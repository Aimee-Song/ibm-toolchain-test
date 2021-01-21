package com.example.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用请求返回结构
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private int code = 200;
    /**
     * 消息
     */
    private String msg = "success";
    /**
     * 结果集
     */
    private T data;

    public static Result ok() {
        return success(200);
    }


    /**
     * 响应成功
     *
     * @return
     */
    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    /**
     * 响应成功
     *
     * @return
     */
    public static Result success(Object data) {
        return success(200, "success", data);
    }


    /**
     * 响应失败
     *
     * @return
     */
    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


    /**
     * 响应失败
     *
     * @return
     */
    public static Result error(String msg) {
        return error(500, msg);
    }


    /**
     * 响应失败
     *
     * @return
     */
    public static Result error(int code) {
        return error(code, "error");
    }

    /**
     * 响应成功
     *
     * @return
     */
    public static Result success(int code, String msg) {
        return success(code, msg, null);
    }


    /**
     * 响应成功
     *
     * @return
     */
    public static Result success(int code) {
        return success(code, "success", null);
    }

    /**
     * 响应成功
     *
     * @return
     */
    public static Result success(String msg) {
        return success(200, msg, null);
    }

}

