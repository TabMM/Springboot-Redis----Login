package com.laity.sso.utils;

import com.laity.sso.entity.pojo.AESProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author D.F Douglas
 * @version 1.0.0
 * @ClassName AESUtil
 * @Description AES加密解密工具类
 * @createTime 2019/7/6/10:15
 * @概述：AES高级加密标准，是对称密钥加密中最流行的算法之一； 工作模式包括：ECB、CBC、CTR、OFB、CFB；
 * @使用范围：该工具类仅支持CBC模式下的： 填充：PKCS5Padding
 * 数据块：128位
 * 密码（key）：32字节长度（例如：12345678901234567890123456789012）
 * 偏移量（iv）：16字节长度（例如：1234567890123456）
 * 输出：base64
 * 字符集：UTF-8
 * @使用方式： String encrypt = AESUtil.encrypt("encData");
 * String decrypt = AESUtil.decrypt("decData");
 * 返回值为空，需要抛出异常
 */
public class AESUtil {
    @Autowired
    AESProperties aesProperties;

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private static final String UTF8 = "UTF-8";
    //指定加密的算法、工作模式和填充方式
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    //加密模式
    private static final String AES = "AES";
    //密钥
    private static String LAITY_SKEY;
    //偏移向量
    private static String OFFSET_VECTOR;

    public AESUtil() {
        LAITY_SKEY =aesProperties.getLAITY_SKEY();
        OFFSET_VECTOR=aesProperties.getOFFSET_VECTOR();
    }

    /**
     * @Author  D.F Douglas
     * @Description //默认加密
     * @Date 10:41 2019/7/6
     * @Param [str]
     * @return java.lang.String
     **/
    public static String encrypt(String str){
        return encrypt(str,LAITY_SKEY,OFFSET_VECTOR);
    }

    /**
     * @Author  D.F Douglas
     * @Description //可自定义加密
     * @Date 10:41 2019/7/6
     * @Param [str, laitySkey 秘钥, offset 偏移量]
     * @return java.lang.String
     **/
    private static String encrypt(String str, String key, String offset) {
        try {
            byte[] bytes =key.getBytes();
            SecretKeySpec keySpec =new SecretKeySpec(bytes,AES);
            IvParameterSpec spec =new IvParameterSpec(offset.getBytes());
            //创建密码器
            Cipher cipher = Cipher.getInstance(AES_CBC);
            //初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE,keySpec,spec);
            //加密
            byte[] encrypted =cipher.doFinal(str.getBytes(UTF8));
            //使用base64 转码
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            logger.error("AES加密异常",str,e);
            return null;
        }
    }
    /**
     * @Author  D.F Douglas
     * @Description //默认解密
     * @Date 10:55 2019/7/6
     * @Param [str]
     * @return java.lang.String
     **/
    public static String decrypt(String str){
        return decrypt(str, LAITY_SKEY, OFFSET_VECTOR);
    }

    /**
     * @Author  D.F Douglas
     * @Description //自定义解密
     * @Date 11:00 2019/7/6
     * @Param [str, laitySkey, offsetVector]
     * @return java.lang.String
     **/
    private static String decrypt(String str, String key, String offset) {
        try {
            byte[] bytes =key.getBytes();
            SecretKeySpec keySpec =new SecretKeySpec(bytes,AES);
            IvParameterSpec spec =new IvParameterSpec(offset.getBytes());
            //实例化密码器
            Cipher cipher = Cipher.getInstance(AES_CBC);
            //初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE,keySpec,spec);
            //解密
            byte[] decrypted =cipher.doFinal(Base64.getDecoder().decode(str));
            String decryptedStr =new String(decrypted,UTF8);
            return decryptedStr;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AES解密异常",str,e);
            return null;
        }
    }

    public static void main(String[] args) {
        String test ="ludeng01245";
        System.out.println(encrypt(test));
        System.out.println(decrypt(test));
    }
}
