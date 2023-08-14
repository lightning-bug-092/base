package com.hius.utils;

public class ObjectUtil {
    public static boolean isNullOrEmpty(Object o){
        if(o == null) return true;

        //1288 is the full-width space
        if(String.valueOf(o).replace((char) 12288,' ').trim().length() == 0) return true;

        if("null".equals(o)) return true;

        return false;
    }

    public static boolean isNotNullOrEmpty(Object o){
        return !isNullOrEmpty(o);
    }
}
