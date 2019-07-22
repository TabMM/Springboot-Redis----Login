package com.laity.sso.dao;

import com.laity.sso.entity.LaitySsoLog;

public interface LaitySsoLogMapper {
    int insert(LaitySsoLog record);

    int insertSelective(LaitySsoLog record);
}