package com.lin.lucene.demo.util;

public class StringUtil {
    
    public static boolean isEmpty(String str) {
        if(str==null||str.equals("")) {
            return true;
        }
        return false;
    }
    
    public static String trimBlank(String str) {
        if(str==null||str.equals("")) {
            return "";
        }
        return str.trim();
    }
    
}
