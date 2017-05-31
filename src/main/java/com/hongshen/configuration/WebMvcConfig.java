package com.hongshen.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by a7289 on 2017/3/10 0010.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("").setViewName("index");
//        registry.addViewController("/home").setViewName("index");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/{userName}/lte/tac").setViewName("../../level1.html");
        registry.addViewController("/{userName}/wcdma/rnc").setViewName("../../level1.html");
        registry.addViewController("/{userName}/lte/tac/{tacName}/node").setViewName("../../../../level2.html");
        registry.addViewController("/{userName}/wcdma/rnc/{tacName}/node").setViewName("../../../../level2.html");
        registry.addViewController("/{userName}/lte/tac/{tacName}/node/{nodeName}/cell").setViewName("../../../../../../level3.html");
        registry.addViewController("/{userName}/wcdma/rnc/{tacName}/node/{nodeName}/cell").setViewName("../../../../../../level3.html");
        registry.addViewController("/setting").setViewName("setting.html");
    }

}
