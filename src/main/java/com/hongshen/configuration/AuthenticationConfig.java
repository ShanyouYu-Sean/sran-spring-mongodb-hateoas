//package com.hongshen.configuration;
//
//import com.hongshen.service.security.UserAuthorizationDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
//
///**
// * Created by a7289 on 2017/4/14 0014.
// */
//@Configuration
//public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {
//
//    @Autowired
//    UserAuthorizationDetailsService userAuthorizationDetailsService;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {


//
//
//        auth.userDetailsService(userAuthorizationDetailsService);
//
//        ldapAuthenticationProviderConfigurer.userSearchFilter("(&(uid={0}))")
//                .userSearchBase("").contextSource(contextSource);
//    }
//}
