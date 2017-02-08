package com.karakal.controller.common;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.karakal.constant.SystemConstant;
import com.karakal.exception.BusinessException;

/**
 * mzk错误码处理 ClassName: ApiController <br/>
 * @author xt(di.xiao@karakal.com.cn)
 * @date 2016年8月3日
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/error")
@EnableConfigurationProperties({ ServerProperties.class })
public class ErrorCodeController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    @Resource
    private Map<String, String> statusMap;

    /**
     * 初始化ErrorCodeController
     * @param errorAttributes
     */
    @Autowired
    public ErrorCodeController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * 404
     * @return
     */
    @RequestMapping(value = "404")
    public Object error404() {
        throw new BusinessException(SystemConstant.NOT_FOUND, statusMap.get(SystemConstant.NOT_FOUND));

    }
    /**
     * 405
     * @return
     */
    @RequestMapping(value = "405")
    public Object error405() {
        throw new BusinessException(SystemConstant.METHOD_NOT_ALLOWED, statusMap.get(SystemConstant.METHOD_NOT_ALLOWED));
    }
    /**
     * 400
     * @return
     */
    @RequestMapping(value = "400")
    public Object error400() {
        throw new BusinessException(SystemConstant.BAD_REQUEST, statusMap.get(SystemConstant.BAD_REQUEST));

    }

    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * 获取错误的信息
     * @param request
     * @param includeStackTrace
     * @return
     */
    @SuppressWarnings("unused")
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    /**
     * 是否包含trace
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 获取错误编码
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return "";
    }
}
