package com.laity.sso.service.impl;

import com.laity.sso.dao.LaitySysUserMapper;
import com.laity.sso.entity.pojo.AESProperties;
import com.laity.sso.entity.vo.ResultVO;
import com.laity.sso.entity.vo.TokenUser;
import com.laity.sso.entity.vo.User;
import com.laity.sso.exception.LoginException;
import com.laity.sso.service.LoginService;
import com.laity.sso.utils.AESUtil;
import com.laity.sso.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @createTime 2019/7/6/9:54
 */
public class LoginServiceImpl implements LoginService {
    @Autowired
    EncryptUtil encryptUtil;
    @Autowired
    AESProperties aesProperties;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    LaitySysUserMapper userMapper;

    @Override
    public ResultVO login(User user) {
        ResultVO resultVO = new ResultVO();
        User record = userMapper.selectByUsername(user);
        if (null != record) {
            if (AESUtil.encrypt(user.getPassword()).equals(record.getPassword())) {
                //用户名做key，token做value 存入redis //30分钟过期
                String token = encryptUtil.DESdecode(user.getUsername() + user.getPassword(), aesProperties.getLAITY_SKEY());
                redisTemplate.opsForValue().set(user.getUsername(), token,30, TimeUnit.MINUTES);
                resultVO.setToken(token);
            } else {
                throw new LoginException("密码错误");
            }
        } else {
            throw new LoginException("用户不存在，请先注册");
        }
        return resultVO;
    }

    @Override
    public ResultVO tokenLogin(TokenUser tokenUser) {
        ResultVO resultVO =new ResultVO();
        Object token =redisTemplate.opsForValue().get(tokenUser.getUsername());
        return null;
    }
}
