package com.laity.sso.entity.vo;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName TokenUser
 * @Description TODO
 * @createTime 2019/7/6/9:59
 */
public class TokenUser extends User {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
