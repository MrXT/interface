package com.karakal.interceptor;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.karakal.util.LogUtil;

/**
 * 接口访问权限验证 ClassName: SecurityInterceptor <br/>
 * Package Name:com.karakal.interceptor
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0 Date:2016年7月28日上午11:02:41 Copyright (c) 2016, manzz.com All
 *          Rights Reserved.
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
        printResponseContext(response);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpServletRequest reqeust = (HttpServletRequest) request;
        printRequestLog(reqeust);
        return true;
    }

    /**
     * 打印请求日志
     * @param request
     */
    public void printRequestLog(HttpServletRequest request) {
        LogUtil.DEBUG("###请求详情日志打印开始###");
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String header = headers.nextElement();
            LogUtil.DEBUG(header+"："+request.getHeader(header));
        }
        LogUtil.DEBUG("请求完整地址:"+request.getRequestURL().toString());
        LogUtil.DEBUG("请求相对地址："+request.getRequestURI());
        LogUtil.DEBUG("请求查询参数："+request.getQueryString());
        StringBuffer sbparam = new StringBuffer("请求参数：");
        for (String key : request.getParameterMap().keySet()) {
            sbparam.append(key + ":" + String.valueOf(request.getParameter(key)) + "----");
        }
        LogUtil.DEBUG(sbparam.toString());
        LogUtil.DEBUG("-----------------");
    }
    /**
     * 打印响应日志
     * @param request
     */
    private void printResponseContext(HttpServletResponse response) {
        LogUtil.DEBUG("###响应详情日志打印开始###");
        Collection<String> headers = response.getHeaderNames();
        for (String header : headers) {
            LogUtil.DEBUG(header+"："+response.getHeader(header));
        }
        LogUtil.DEBUG("------------------");
    }
}
