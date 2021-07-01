package com.github.cosycode.codedict.util;

import lombok.NonNull;

/**
 * <b>Description : </b> 简单的字符出处理类
 * <p>
 * <b>created in </b> 2020/2/13
 *
 * @author CPF
 * @since 1.0
 **/
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(@NonNull String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

}
