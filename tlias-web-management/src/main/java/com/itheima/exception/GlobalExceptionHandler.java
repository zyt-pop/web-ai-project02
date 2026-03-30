package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e){
        log.warn("业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("系统异常,请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");

        return Result.error(arr[2]+"已存在");
    }
    @ExceptionHandler
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("参数类型转换错误：{}", e.getMessage());
        String paramName = e.getName();
        return Result.error("参数 '" + paramName + "' 格式错误，请检查是否为空或格式不正确");
    }

    @ExceptionHandler
    public Result handleNumberFormatException(NumberFormatException e) {
        log.warn("数字格式转换错误：{}", e.getMessage());
        return Result.error("参数格式错误，不能为 null 或无效的数字格式");
    }
}
