package com.cp.utils;


import java.util.Base64;

/**
 * cp 2019-09-10  19:44
 */
public class Base64Utils {
    //加密
    public static String encode(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    //解密
    public static String decode(String str){
        return new String(Base64.getDecoder().decode(str.getBytes()));
    }
}
