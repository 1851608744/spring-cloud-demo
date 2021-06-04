//package com.yw.demo.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//import javax.sql.DataSource;
//
///**
// * 令牌访问端点配置
// * @author yangwei
// * @data 2021/06/03
// **/
////@Configuration
////@EnableAuthorizationServer
//public class AuthorizationCodeServiceConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//
//
//    /**
//     * 令牌访问端点
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                //认证管理器
//                .authenticationManager(authenticationManager)
//                //授权码服务
//                .authorizationCodeServices(authorizationCodeServices)
//                //令牌管理服务
//                .tokenServices(tokenServices())
//                //设置POST访问
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
//    }
//
//    /**
//     * 设置授权码模式的授权码如何存储，暂时采用内存方式
//     * @return
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }
//
//
//    @Bean
//    public AuthorizationServerTokenServices tokenServices() {
//        DefaultTokenServices services = new DefaultTokenServices();
//        //客户端详情服务
//        services.setClientDetailsService(clientDetailsService);
//        //支持刷新
//        services.setSupportRefreshToken(true);
//        //令牌类型
//        services.setTokenStore(tokenStore);
//        //令牌默认有效时间2小时
//        services.setAccessTokenValiditySeconds(7200);
//        //刷新令牌默认有效期3天
//        services.setRefreshTokenValiditySeconds(259200);
//        return services;
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        super.configure(security);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //授权码模式
//        clients.jdbc(dataSource)
//                .withClient("client_code")
//                .secret(passwordEncoder.encode("123455"))
//                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "implicit")
//                .scopes("all")
//                .authorities("ROLE_ADMIN")
//                .redirectUris("http://www.baidu.com")
//                .accessTokenValiditySeconds(1200)
//                .refreshTokenValiditySeconds(50000);
//    }
//}
