package com.yw.demo.common.exception;

import com.yw.demo.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * @author yangwei
 * @description
 * @data 2021/07/23
 **/
public class ParamException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponseCode errorCode;
    private String msg;

    public ParamException(ResponseCode errorCode, String msg, String... args) {
        this.errorCode = errorCode;
        if (args == null || args.length == 0) {
            this.msg = msg;
        } else {
            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.replace(sb.indexOf("{"), sb.indexOf("}") + 1,
                        new StringBuilder("[").append(arg).append("]").toString());
            }
            this.msg = sb.toString();
        }
    }

    public ParamException(ResponseCode errorCode) {
        this.msg = errorCode.getMsg();
        this.errorCode = errorCode;
    }


    public ResponseCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ResponseCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
