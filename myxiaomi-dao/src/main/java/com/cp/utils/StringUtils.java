package com.cp.utils;

/**
 * cp 2019-09-10  19:09
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if(str==null||str.trim().length()==0){
            return true;
        }else {
            return false;
        }
    }
}
