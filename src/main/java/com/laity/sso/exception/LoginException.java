package com.laity.sso.exception;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName LoginException
 * @Description TODO
 * @createTime 2019/7/6/11:27
 */
public class LoginException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginException(final String message) {
        super(message);
    }
}
