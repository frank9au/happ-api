package com.happy.handler;

import com.happy.base.BaseRespDTO;
import com.happy.enums.ReturnEnums;
import com.happy.exception.BizException;
import com.happy.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public BaseRespDTO bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getSubMessage());
        return ResultUtils.bizFail();
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public BaseRespDTO exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ResultUtils.bizFail(ReturnEnums.OP_FAIL502.getCode(), e.getMessage());
    }

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseRespDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultUtils.bizFail(ReturnEnums.SUB_FAIL610.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public BaseRespDTO handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return ResultUtils.bizFail(ReturnEnums.SUB_FAIL610.getCode(), e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseRespDTO handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ResultUtils.bizFail(ReturnEnums.SUB_FAIL610.getCode(), e.getMessage());
    }
}
