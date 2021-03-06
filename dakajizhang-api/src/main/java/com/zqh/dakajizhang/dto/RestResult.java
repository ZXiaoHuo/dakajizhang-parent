package com.zqh.dakajizhang.dto;

import lombok.Data;

/**
 * @author zhangqh
 * @date 2021/8/31 0031 14:33
 */
@Data
public class RestResult {
    private String code;
    private String msg;
    private Object data;

    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "500";
    private static final String SUCCESS_MSG = "操作成功";
    private static final String ERROR_MSG = "操作失败";

    public static final String SERVER_NOT_CONNECT = "555";//服务器断开连接
    public static final String SERVER_TIMEOUT = "888";//服务连接超时


    private RestResult(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResult success() {
        return new RestResult(SUCCESS_CODE,SUCCESS_MSG,null);
    }

    public static RestResult success(Object data) {
        return new RestResult(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    public static RestResult success(String code,String msg) {
        return new RestResult(code,msg,null);
    }

    public static RestResult success(String code,String msg,Object data) {
        return new RestResult(code,msg,data);
    }

    public static RestResult error() {
        return new RestResult(ERROR_CODE,ERROR_MSG,null);
    }

    public static RestResult error(String msg) {
        return new RestResult(ERROR_CODE,msg,null);
    }

    public static RestResult error(String code,String msg) {
        return new RestResult(code,msg,null);
    }

    public static RestResult error(String code,String msg,Object data) {
        return new RestResult(code,msg,data);
    }
}
