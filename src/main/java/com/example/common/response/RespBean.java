package com.example.common.response;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.DocFlavor;
import java.io.Serializable;

@Data
public class RespBean implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public static RespBean ok(){
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.OK.value();
        respBean.msg = "ok";
        respBean.data = null;
        return respBean;
    }

    public static RespBean ok(Object data) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.OK.value();
        respBean.msg = "ok";
        respBean.data = data;
        return respBean;
    }

    public static RespBean ok(String msg, Object data) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.OK.value();
        respBean.msg = msg;
        respBean.data = data;
        return respBean;
    }

    public static RespBean error() {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        respBean.msg = "internal server error";
        respBean.data = null;
        return respBean;
    }

    public static RespBean error(Throwable throwable) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        respBean.msg = "internal server error";
        respBean.data = throwable;
        return respBean;
    }

    public static RespBean error(Object data) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        respBean.msg = "internal server error";
        respBean.data = data;
        return respBean;
    }

    public static RespBean error(String msg) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        respBean.msg = msg;
        respBean.data = null;
        return respBean;
    }

    public static RespBean error(String msg, Throwable throwable) {
        RespBean respBean = new RespBean();
        respBean.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        respBean.msg = msg;
        respBean.data = throwable;
        return respBean;
    }

    public static RespBean build(Integer code, String msg,Object data) {
        RespBean respBean = new RespBean();
        respBean.code = code;
        respBean.msg = msg;
        respBean.data = data;
        return respBean;
    }

    public static RespBean build(RespCode respCode, Object data) {
        RespBean respBean = new RespBean();
        respBean.code = respCode.getCode();
        respBean.msg = respCode.getMsg();
        respBean.data = data;
        return respBean;
    }
}
