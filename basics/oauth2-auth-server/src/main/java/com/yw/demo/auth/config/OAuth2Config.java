package com.yw.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    //@Autowired
    //private PasswordEncoder passwordEncoder;
    //
    //@Autowired
    //private AuthenticationManager authenticationManager;
    //
    //@Autowired
    //private TokenStore tokenStore;



    //@Override
    //public void configure(ClientDetailsServiceConfigurer  clients) throws Exception {
    //    clients.inMemory()
    //            .withClient("login")
    //            .secret(passwordEncoder.encode("user-secret-8888"))
    //            .authorizedGrantTypes("refresh_token", "authorization_code", "password")
    //            .accessTokenValiditySeconds(3600)
    //            .scopes("all")
    //            .and()
    //            .withClient("user—client")
    //            .secret(passwordEncoder.encode("user-secret-8888"))
    //            .authorizedGrantTypes("refresh_token", "authorization_code", "password")
    //            .accessTokenValiditySeconds(3600)
    //            .scopes("all");
    //}



}
