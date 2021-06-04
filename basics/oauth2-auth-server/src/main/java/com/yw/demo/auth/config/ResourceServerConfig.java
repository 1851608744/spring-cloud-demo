package com.yw.demo.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Configuration
//使用order属性，设置该类在spring容器中的加载顺序
@Order(6)
//这个类表明了此应用是OAuth2 的资源服务器，此处主要指定了受资源服务器保护的资源链接
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //禁用了 csrf 功能
        http.csrf().disable()
                //限定签名成功的请求
                .authorizeRequests()
                //必须认证过后才可以访问
                //.antMatchers("/test/**","/admin/**").authenticated()
                // /decision/** /govern/** 需要 USER ADMIN 权限
                //.antMatchers("/decision/**","/govern/**").hasAnyRole("USER","ADMIN")
                // /admin/** 需要 ADMIN 权限
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //其他没有限定的请求，允许随意访问
                .anyRequest().permitAll()
                //对于没有配置权限的其他请求允许匿名访问
                .and().anonymous();
                //使用spring security 默认登录页面
                //.and().formLogin();
                //启用http基础验证
                //.and().httpBasic();


    }
}
