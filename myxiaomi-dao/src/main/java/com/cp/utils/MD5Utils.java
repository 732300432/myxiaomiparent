package com.cp.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * cp 2019-09-10  19:12
 */
public class MD5Utils {
    public static String md5(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("utf-8"));
            byte[] digest = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            return bigInteger.toString(16);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String decodMd5(String str){
//        BigInteger bigInteger = new BigInteger(1,str.getBytes());
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        messageDigest.update(str.getBytes());
//
//    }
}
