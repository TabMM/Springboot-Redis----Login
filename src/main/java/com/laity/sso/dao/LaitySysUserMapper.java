package com.laity.sso.dao;

import com.laity.sso.entity.LaitySysUser;
import com.laity.sso.entity.vo.User;

public interface LaitySysUserMapper {
    int insert(LaitySysUser record);

    int insertSelective(LaitySysUser record);
    /*
     * @Author  D.F Douglas
     * @Description //根据用户名查找用户
     * @Date 10:05 2019/7/6
     * @Param [user]
     * @return com.laity.sso.entity.vo.User
     **/
    User selectByUsername(User user);
}
