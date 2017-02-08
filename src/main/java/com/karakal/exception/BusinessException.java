package com.karakal.exception;

/**
 * 与用户操作或实际业务逻辑相关的异常<br>
 * 由框架统一捕获并处理,将异常信息显示给用户 ClassName: BusinessException <br/>
 * Package Name:com.karakal.exception
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0 Date:2016年7月28日下午1:54:35 Copyright (c) 2016, manzz.com All
 *          Rights Reserved.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4942849136285353014L;

    private String errorCode = null;

    public BusinessException() {
        super("操作异常");
    }

    public BusinessException(Exception e) {
        super(e);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, Throwable arg) {
        super(message, arg);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}