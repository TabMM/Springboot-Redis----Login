package com.laity.sso.entity.vo;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName ResultVO
 * @Description TODO
 * @createTime 2019/7/5/18:18
 */
public class ResultVO {
    private Integer code;
    private String msg = "ok";
    private String token;
    private Object object;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
