//package com.yw.demo.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
///**
// * @author yangwei
// * @data 2021/06/04
// **/
////@Configuration
////@EnableAuthorizationServer
//public class AuthorizationPasswordServerConfig extends AuthorizationServerConfigurerAdapter {
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private DataSource dataSource;
//
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public TokenStore tokenStore() {
//        // return new InMemoryTokenStore();  使用内存中的token store
//        return new JdbcTokenStore(dataSource); // 使用jdbctoken store
//    }
//
//    /**
//     * 用来配置客户端详情服务(ClientDetailsService)，客户端详情信息在 这里进行初始化，
//     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource)
//                //client_id (必须的)用来标识客户的Id
//                .withClient("client")
//                //客户端密钥
//                .secret(passwordEncoder.encode("123456"))
//                //资源列表
//                //.resourceIds("res1")
//                //授权类型
//                .authorizedGrantTypes("password", "refresh_token")
//                //客户端可以使用的权限（基于Spring Security authorities）
//                .authorities("ROLE_ADMIN", "ROLE_USER")
//                //限制客户段的访问范围，如果为空（默认）拥有全部的访问范围
//                .scopes("read", "write")
//                //跳转到授权页面
//                //.autoApprove(false)
//                //添加验证回调地址
//                //.redirectUris("http://www.baidu.com")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(7200);
//    }
//
//    /**
//     * 用来配置令牌（token）的访问端点和令牌服务（token services）
//     * @param security
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
//                // 允许表单登录
//                .allowFormAuthenticationForClients();
//    }
//
//    /**
//     * 用来配置令牌端点的安全约束
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore())
//                // authenticationManager:认证管理器，当你选择了资源所有者密码(password)授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 对象
//                .authenticationManager(authenticationManager)
//                // userDetailsService:如果你设置了这个属性的话，那说明你有一个自己的 UserDetailsService 接口的实现，
//                // 或者你可以把这个东西设置到全局域上面去(例如 GlobalAuthenticationManagerConfigurer 这个配置对 象)，
//                // 当你设置了这个之后，那么 “refresh_token” 即刷新令牌授权类型模式的流程中就会包含一个检查，用 来确保这个账号是否仍然有效，假如说你禁用了这个账户的话。
//                .userDetailsService(userDetailsService)
//                // authorizationCodeServices:这个属性是用来设置授权码服务的(即 AuthorizationCodeServices 的实例对 象)，主要用于 “authorization_code” 授权码类型模式。
//                //.authorizationCodeServices(authorizationCodeServices)
//                // tokenGranter:当你设置了这个东西(即 TokenGranter 接口实现)，那么授权将会交由你来完全掌控，并 且会忽略掉上面的这几个属性，这个属性一般是用作拓展用途的，即标准的四种授权模式已经满足不了你的 需求的时候，才会考虑使用这个
//                //.tokenGranter()
//                // 配置端点URL链接，它有两个参数:
//                //第一个参数:String 类型的，这个端点URL的默认链接。
//                //第二个参数:String 类型的，你要进行替代的URL链接。
//                //以上的参数都将以 “/” 字符为开始的字符串
//                ///oauth/authorize:授权端点。
//                ///oauth/token:令牌端点。
//                ///oauth/confirm_access:用户确认授权提交端点。
//                ///oauth/error:授权服务错误信息端点。
//                ///oauth/check_token:用于资源服务访问的令牌解析端点。
//                ///oauth/token_key:提供公有密匙的端点，如果你使用JWT令牌的话。
//                //需要注意的是授权端点这个URL应该被Spring Security保护起来只供授权用户访问
//                //.pathMapping("/oauth/token","/oauth/tokee")
//
//                // 设置允许get，post
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//    }
//}
