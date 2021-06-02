package com.yw.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Configuration
// 这个注解告诉 Spring 这个应用是 OAuth2 的授权服务器
@EnableAuthorizationServer
// 提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        // return new InMemoryTokenStore();  使用内存中的token store
        return new JdbcTokenStore(dataSource); // 使用jdbctoken store
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource)
                .withClient("client")
                .secret(passwordEncoder().encode("123456"))
                // 允许授权范围
                .authorizedGrantTypes("password", "refresh_token")
                // 客户端可以使用的权限
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .scopes("read", "write")
                .accessTokenValiditySeconds(7200)
                .refreshTokenValiditySeconds(7200)
                .and().withClient("client_1")
                .secret(passwordEncoder().encode("123456"))
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .authorities("client_credentials")
                .accessTokenValiditySeconds(72000);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();// 允许表单登录
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                // 必须设置 UserDetailsService否则刷新token 时会报错
                .userDetailsService(userDetailsService)
                // 设置允许get，post
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

    }
}
