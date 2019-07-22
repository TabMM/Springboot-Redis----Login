package com.laity.sso.service;

import com.laity.sso.entity.vo.ResultVO;
import com.laity.sso.entity.vo.TokenUser;
import com.laity.sso.entity.vo.User;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName LoginService
 * @Description TODO
 * @createTime 2019/7/6/9:53
 */
public interface LoginService {
    ResultVO login(User user);

    ResultVO tokenLogin(TokenUser tokenUser);
}
