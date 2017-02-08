package com.karakal.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.bean.ResponseJson;
import com.karakal.constant.SystemConstant;
import com.karakal.exception.BusinessException;
import com.karakal.util.LogUtil;

/**
 * controller层的aop，主要用来封装返回的json数据格式 ClassName: ControllerAOP <br/>
 * Package Name:com.karakal.interceptor
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0 Date:2016年7月28日上午11:58:22 Copyright (c) 2016, manzz.com All
 *          Rights Reserved.
 */
@Aspect
@Configuration
public class ControllerAOP {

    // 定义切点Pointcut
    @Pointcut("execution(* com.karakal.controller.*.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        if (target.getClass().isAnnotationPresent(RestController.class)|| method.isAnnotationPresent(ResponseBody.class)) {
            ResponseJson json = new ResponseJson();
            Date beforeDate = new Date();
            // result的值就是被拦截方法的返回值
            try {
                Object result = pjp.proceed();
                if (result instanceof ResponseJson) {
                    return result;
                } else {
                    json.setData(result);
                }
                json.setStatus(SystemConstant.SUCCESS);
                json.setMsg(SUCCESS);
                // LogUtil.DEBUG("返回结果：" + JSON.toJSONString(json,
                // SerializerFeature.DisableCircularReferenceDetect));// 打印响应结果
                LogUtil.DEBUG("请求耗时(ms)：" + (new Date().getTime() - beforeDate.getTime()));
            } catch (BusinessException e) {
                LogUtil.LOGEXCEPTION(e);
                json.setMsg(e.getMessage());
                json.setStatus(SystemConstant.FAIL);
                if(StringUtils.isNotBlank(e.getErrorCode())){
                    json.setStatus(Integer.parseInt(e.getErrorCode()));
                }
            } catch (Exception e) {
                LogUtil.LOGEXCEPTION(e);
                json.setMsg(FAIL);
                json.setStatus(SystemConstant.FAIL);
            } catch (Error e) {
                LogUtil.LOGEXCEPTION(e);
                json.setMsg(FAIL);
                json.setStatus(SystemConstant.FAIL);
            }
            return json;
        }
        return pjp.proceed();
    }

    private final static String SUCCESS = "操作成功!";

    private final static String FAIL = "操作失败!";

}
