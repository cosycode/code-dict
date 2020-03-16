package com.github.codedict.util;

/**
 * <b>Description : </b> 简单的字符出处理类
 *
 * @author CPF
 * @since 1.0
 * @date 2020/2/13 16:43
 **/
public interface StringUtils {

    static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
