package com.yw.demo.spring.service.impl;

import com.yw.demo.spring.service.MessageService;

/**
 * @author yangwei
 * @data 2021/06/15
 **/
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "Hello World !!!!";
    }

}
