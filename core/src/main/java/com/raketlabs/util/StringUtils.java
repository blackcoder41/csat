package com.raketlabs.util;

public class StringUtils {
    
    
    public static String left (String str, int max) {
        return str.substring(0, Math.min(str.length(), max));
    }
}
