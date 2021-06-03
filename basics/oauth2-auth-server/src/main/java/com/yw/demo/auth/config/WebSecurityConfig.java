package com.yw.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 配置用户签名服务 主要是user-details 机制，
     * @param auth 签名管理器构造器，用于构建用户具体权限控制
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 用来构建Filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 用来配置拦截保护的请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 不拦截 oauth 开发的资源
        http.csrf().disable();
        //使HttpSecurity接收以"/login/","/oauth/"开头请求。
        http.requestMatchers()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin();

        //          security配置
        //http.csrf().disable() //禁用了 csrf 功能
        //        // 限定签名成功的请求
        //        .authorizeRequests()
        //        //对decision和govern 下的接口 需要 USER 或者 ADMIN 权限
        //        .antMatchers("/decision/**", "/govern/**").hasAnyRole("USER", "ADMIN")
        //        //admin/login 不限定
        //        .antMatchers("/admin/login").permitAll()
        //        //对admin下的接口 需要ADMIN权限
        //        .antMatchers("/admin/**").hasRole("ADMIN")
        //        //不拦截 oauth 开放的资源
        //        .antMatchers("/oauth/**").permitAll()
        //        //其他没有限定的请求，允许访问
        //        .anyRequest().permitAll()
        //        //对于没有配置权限的其他请求允许匿名访问
        //        .and().anonymous()
        //        //使用 spring security 默认登录页面
        //        .and().formLogin()
        //        //启用http 基础验证
        //        .and().httpBasic();
    }
}
