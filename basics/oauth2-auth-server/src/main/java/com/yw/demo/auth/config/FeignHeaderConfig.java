package com.yw.demo.auth.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign的header配置
 */
@Configuration
public class FeignHeaderConfig {

    //feign请求转发是需要从原请求头复制的header信息
    final String[] copyHeaders = new String[]{"cookie"};
    /**
     * 重写后feign转发请求会携带原请求的Head信息
     */
    private class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate requestTemplate) {
            // SystemConstant.COOKIE="Cookie"
            // new DefaultTokenHandler().getTokenInCookie() 为获取到的Cookie信息
            /**
             spring-boot-starter-webflux 框架走下述方法
             <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-webflux</artifactId>
             </dependency>
             */
            //requestTemplate.header(SystemConstant.COOKIE, new DefaultTokenHandler().getTokenInCookie());


            /**
             spring-boot-starter-web 框架走下面注释方法
             <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-web</artifactId>
             </dependency>
             
             
             */
            /*
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        if (iscopy(name)) {
                            requestTemplate.header(name, values);
                        }
                    }
                }
            }
            */
        }
    }

    /**
     * @param name
     * @return
     */
    private Boolean iscopy(String name) {
        for (String header : copyHeaders) {
            if (header.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
    
    /**
     * 开启feign调用日志打印的debug模式
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}