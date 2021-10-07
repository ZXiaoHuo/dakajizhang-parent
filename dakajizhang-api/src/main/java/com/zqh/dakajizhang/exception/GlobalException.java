package com.zqh.dakajizhang.exception;

import lombok.Data;

/**
 * @author zhangqh
 * @date 2021/9/15 0015 10:59
 */
@Data
public class GlobalException extends RuntimeException{

    private String msg;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
