package com.yw.demo.common.dto;

import com.yw.demo.common.enums.ResponseCode;
import lombok.Data;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Data
public class ResponseDTO<T> {

    private boolean success;
    private String code;
    private String msg;
    private T data;

    public ResponseDTO(){}

    public ResponseDTO(ResponseCode responseCode) {
        code = responseCode.getCode();
        msg = responseCode.getMsg();
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
        //上述会使得success 值为false
        success = responseCode.isSuccess();
    }

    public ResponseDTO(ResponseCode responseCode, T data){
        code = responseCode.getCode();
        msg = responseCode.getMsg();
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
        //上述会使得success 值为false
        success = responseCode.isSuccess();
        this.data = data;
    }

    public ResponseDTO(ResponseCode responseCode, String msg) {
        code = responseCode.getCode();
        this.msg = msg;
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
    }


}
