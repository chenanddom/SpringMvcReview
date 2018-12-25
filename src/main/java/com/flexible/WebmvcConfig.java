package com.flexible;

import com.flexible.converter.MyMessageConverter;
import com.flexible.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-21
 * Time: 14:40
 */
@Configuration
@EnableWebMvc
@EnableScheduling//开启计划任务
@ComponentScan("com.flexible")
public class WebmvcConfig extends WebMvcConfigurerAdapter{
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 在开发中如果涉及到没有任何业务的跳转可以奖跳转统一的在配置类设置。
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        //添加了转向upload页面的viewController
        registry.addViewController("/toUpload").setViewName("/upload");
        //跳转到自定义HttpmessageConverter的测试页面
        registry.addViewController("/converter").setViewName("/converter");
        //跳转到sse
        registry.addViewController("/sse").setViewName("/sse");
        //条转到异步获取数据
        registry.addViewController("/async").setViewName("/async");
    }
    /**
     * 访问时如果加了后缀就会在访问的时候后缀会被忽略
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);//后缀不会被忽略
    }
    /**
     * 静态资源映射配置
     * 添加处理程序来提供静态资源，如图像、js和css
     *来自web应用程序根目录、类路径下的特定位置的文件，
     *和其他。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations(
                "classpath:/assets/");//文件放置的地方
    }
    /**
     * 生成一个拦截器的bean
     * @return
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /**
     * MultipartResolver配置
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 注册自定义的HttpMessageConverters
     * 仅添加一个自定义的HttpMessageConverter，不覆盖默认注册HttpMessageConverter
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(converter());
    }
    //configureMessageConverters:重载会覆盖调SpringMVC默认注册的多个HttpMessageConverter.
/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }*/

    /**
     * 自定义Bean
     * @return
     */
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}
