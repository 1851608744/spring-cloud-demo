package com.yw.demo.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yw.demo.service.IpInfoService;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yangwei
 * @data 2021/06/17
 **/
@Service
public class IpInfoServiceImpl implements IpInfoService {



    @SentinelResource(value = "ip_info", blockHandler = "exceptionHandler")
    @Override
    public String getIpInfo() {
        String result = "";
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            result = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String exceptionHandler(BlockException ex) {
        ex.printStackTrace();
        return "请求过于频繁";
    }
}
