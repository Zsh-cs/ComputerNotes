package com.zsh.controller;

import com.zsh.exception.BuisnessException;
import com.zsh.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 项目异常统一处理
 */
@RestControllerAdvice// REST风格
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)// 拦截系统异常并处理
    public Result handleSystemException(SystemException e) {
        // 1.记录日志
        // 2.发送消息给运维人员
        // 3.发送邮件给开发人员
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(BuisnessException.class)// 拦截业务异常并处理
    public Result handleBusinessException(BuisnessException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)// 拦截其他异常并处理
    public Result handleException(Exception e) {
        // 1.记录日志
        // 2.发送消息给运维人员
        // 3.发送邮件给开发人员
        return new Result(Code.UNKNOWN_ERR, null, "系统繁忙，请稍后再试！");
    }
}
