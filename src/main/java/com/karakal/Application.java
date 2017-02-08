package com.karakal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.justobjects.pushlet.servlet.Pushlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;





import com.alibaba.fastjson.JSON;
import com.karakal.bean.JPush;
import com.karakal.interceptor.PathFilter;

@RestController
@SpringBootApplication
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})//不用springboot的默认文件配置
@EnableAsync
public class Application extends WebMvcConfigurerAdapter{
    @Autowired
    private JPush jPush;
    
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	@RequestMapping("/index")
	public String index() {
	    String[] alias = new String[1];
	    alias[0] = "bfb4c1e7da17452398b16bc1e80ae615";
//	    alias[1] = "xiaoti";
	    String rid = "12312";
	    String type = "10003";
	    String content="会议消息通知!";
	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("rid", rid);
	    map.put("type",type );
	    map.put("content", content);
	    jPush.sendMsg(alias, JSON.toJSONString(map));
		return "It works!";
	}
	@Bean
    public FilterRegistrationBean basicFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new PathFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){//注册页面推送
	    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
	    servletRegistrationBean.setServlet(new Pushlet());
	    servletRegistrationBean.setOrder(3);
	    List<String> urlPatterns = new ArrayList<String>();
	    urlPatterns.add("/resources/pushlet.srv");
	    servletRegistrationBean.setUrlMappings(urlPatterns);
	    return servletRegistrationBean;
	}
}
