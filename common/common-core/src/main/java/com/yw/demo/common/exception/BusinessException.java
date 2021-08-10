package com.yw.demo.common.exception;

import com.yw.demo.common.enums.ResponseCode;

import java.io.BufferedReader;

/**
 * @author yangwei
 * @description
 * @data 2021/07/23
 **/
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ResponseCode responseCode;

    private DiyRespStatus respStatus;

    private String msg;

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.toString());
        this.responseCode = errorCode;
    }

    public BusinessException(ResponseCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.responseCode = errorCode;
    }


    public BusinessException(ResponseCode errorCode, DiyRespStatus status) {
        super(errorCode.toString());
        this.responseCode = errorCode;
        this.respStatus = status;
    }

    public BusinessException(ResponseCode errorCode, DiyRespStatus status, Throwable cause) {
        super(errorCode.toString(), cause);
        this.responseCode = errorCode;
        this.respStatus = status;
    }

    public BusinessException(ResponseCode errorCode, String msg) {
        super(errorCode.toString());
        this.responseCode = errorCode;
        this.msg = msg;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public DiyRespStatus getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(DiyRespStatus respStatus) {
        this.respStatus = respStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "responseCode=" + responseCode +
                ", respStatus=" + respStatus +
                ", msg='" + msg + '\'' +
                '}';
    }
}
