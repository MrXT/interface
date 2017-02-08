package com.karakal.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.karakal.util.LogUtil;

public class PathFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (servletRequest.getRequestURI().indexOf("/resources/pushlet.srv") == -1) {
            StringBuffer sbparam = new StringBuffer("请求地址："+servletRequest.getRequestURI()+",请求参数：");
            for (String key : request.getParameterMap().keySet()) {
                sbparam.append(key + ":" + String.valueOf(request.getParameter(key)) + "--");
            }
            LogUtil.DEBUG(sbparam.toString());
        }
        arg2.doFilter(request, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
