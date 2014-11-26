package com.arhiser.wedding.utils;

/**
 * Created by arkhipov on 26.11.2014.
 */
public class StringUtils {
    public static boolean isNumber(String str) {
        return isInteger(str, 10);
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
