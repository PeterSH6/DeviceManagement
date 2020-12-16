package com.example.common.exception;
import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author https://github.com/anlowee
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BusinessExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public RespBean handleBusinessException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        RespCode code = exception.getCode();
        return RespBean.build(code, null);
    }

}
