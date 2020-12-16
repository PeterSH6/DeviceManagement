package com.example.common.exception;

import com.example.common.response.RespCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author https://github.com/anlowee
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private RespCode code;

    public BusinessException(RespCode code) {
        this.code = code;
    }

    public BusinessException(Throwable cause, RespCode code) {
        super(cause);
        this.code = code;
    }

}
