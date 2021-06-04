package com.yw.demo.auth.config;

import com.yw.demo.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@Order(2)
@EnableWebSecurity
/**
 * 需要保护/oauth/authorize以及/oauth/confirm_access这两个endpoint，当然主要是/oauth/authorize这个。
 *
 * 由于其他几个/oauth/开头的认证endpoint配置的认证优先级高于默认的WebSecurityConfigurerAdapter配置(order=100)，
 * 因此默认的可以这样配置
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
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
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 用来配置拦截保护的请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http
                //限定签名成功的请求
                .authorizeRequests()
                // 签名成功后可访问
                .antMatchers("/oauth/**").authenticated()
                .and().authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                //其他没有限定的请求
                .anyRequest().permitAll()
                //对于没有限定的其他请求允许匿名访问
                .and().anonymous()
                //使用spring security 默认的登录页面
                .and().formLogin().permitAll()
                //启用http 基础验证
                .and().httpBasic();

        //// 不拦截 oauth 开发的资源
        //http.csrf().disable();
        ////使HttpSecurity接收以"/login/","/oauth/"开头请求。
        //http.requestMatchers()
        //        .antMatchers("/oauth/**", "/login/**", "/logout/**")
        //        .and()
        //        .authorizeRequests()
        //        .antMatchers("/oauth/**").authenticated()
        //        .and()
        //        .formLogin();

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
