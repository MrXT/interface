package com.karakal.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 业务相关配置类 ClassName: BusinessConfig <br/>
 * Package Name:com.karakal.config
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0 Date:2016年7月28日下午2:00:36 Copyright (c) 2016, manzz.com All
 *          Rights Reserved.
 */
@Configuration
public class BusinessConfig {

    @Bean(name = "statusMap")
    public Map<String, String> statusMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("000000", "查询成功");
        map.put("200001", "输入的必选参数不能为空");
        map.put("200002", "参数格式错误");
        map.put("100002", "系统忙");
        map.put("100005", "数据库操作异常");
        map.put("100006", "数据库操作失败");
        map.put("100007", "请求过于频繁");
        map.put("100010", "Cookie中kuid不能为空！");
        map.put("100011", "上传文件不能为空！");
        map.put("400", "BAD_REQUEST,请求参数错误");
        map.put("405", "METHOD_NOT_ALLOWED,请求方法错误");
        map.put("404", "NOT_FOUND,请求地址没找到");
        return map;
    }

    /**
     * 配置文件上传bean
     * @return
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
    /**
     * 重写spring boot的error接口
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
                container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405"));
                container.setSessionTimeout(30*60);//单位s
            }
        };
    }

}
