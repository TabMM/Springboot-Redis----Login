package com.laity.sso.controller;

import com.laity.sso.entity.vo.ResultVO;
import com.laity.sso.entity.vo.TokenUser;
import com.laity.sso.entity.vo.User;
import com.laity.sso.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName LoginController
 * @Description TODO
 * @createTime 2019/7/5/18:13
 */
@RestController
@RequestMapping(value = "/laity")
public class LoginController {
    private LoginService loginService;
    /*
     * @Author  D.F Douglas
     * @Description //普通登录
     * @Date 9:48 2019/7/6
     * @Param
     * @return
     **/
    @RequestMapping(value = "/login")
    public ResultVO login(User user){
        ResultVO resultVO =loginService.login(user);
        return resultVO;
    }
    /*
     * @Author  D.F Douglas
     * @Description //Token 登录
     * @Date 9:56 2019/7/6
     * @Param
     * @return
     **/
    public ResultVO tokenLogin(TokenUser tokenUser){
        ResultVO resultVO =loginService.tokenLogin(tokenUser);
        return  resultVO;
    }
}
