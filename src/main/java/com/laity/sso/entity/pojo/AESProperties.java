package com.laity.sso.entity.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName AESProperties
 * @Description TODO
 * @createTime 2019/7/6/11:11
 */
@Component
public class AESProperties {
    //密钥
    @Value("${laity.aes.key}")
    private String LAITY_SKEY;
    //偏移向量
    @Value("${laity.aes.vector16}")
    private String OFFSET_VECTOR;

    public String getLAITY_SKEY() {
        return LAITY_SKEY;
    }

    public void setLAITY_SKEY(String LAITY_SKEY) {
        this.LAITY_SKEY = LAITY_SKEY;
    }

    public String getOFFSET_VECTOR() {
        return OFFSET_VECTOR;
    }

    public void setOFFSET_VECTOR(String OFFSET_VECTOR) {
        this.OFFSET_VECTOR = OFFSET_VECTOR;
    }

    @Override
    public String toString() {
        return "AESProperties{" +
                "LAITY_SKEY='" + LAITY_SKEY + '\'' +
                ", OFFSET_VECTOR='" + OFFSET_VECTOR + '\'' +
                '}';
    }
}
