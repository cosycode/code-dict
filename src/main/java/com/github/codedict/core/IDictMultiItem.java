package com.github.codedict.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2020/3/16 11:13
 */
public class IDictMultiItem {

    public static final String SPLIT_SYMBOL = ",";

    private IDictMultiItem() {
    }

    /**
     * 通过value来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param value 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配value的枚举对象
     */
    static <T extends IDictItem> List<T> getByValue(Class<T> enumClass, String value) {
        if (enumClass == null || value.isEmpty()) {
            return Collections.emptyList();
        }
        String[] arr = value.split(SPLIT_SYMBOL);
        List<T> list = new ArrayList<>(arr.length);
        for (String str : arr) {
            //通过反射取出Enum所有常量的属性值
            for (T each : enumClass.getEnumConstants()) {
                //利用value进行循环比较，获取对应的枚举
                if (str.equals(each.value())) {
                    list.add(each);
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 通过label来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param label 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配label的枚举对象
     */
    static <T extends IDictItem> List<T> getByLabel(Class<T> enumClass, String label) {
        if (enumClass == null || label.isEmpty()) {
            return Collections.emptyList();
        }
        String[] arr = label.split(SPLIT_SYMBOL);
        List<T> list = new ArrayList<>(arr.length);
        for (String str : arr) {
            //通过反射取出Enum所有常量的属性值
            for (T each : enumClass.getEnumConstants()) {
                //利用value进行循环比较，获取对应的枚举
                if (str.equals(each.label())) {
                    list.add(each);
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 通过 value 来获取 label
     *
     * @param enumClass 枚举类
     * @param value 枚举代码
     * @param <T> 模板类型
     * @return 如果 value为空
     */
    static <T extends IDictItem> String getLabelByValue(Class<T> enumClass, String value) {
        if (value == null) {
            return "";
        }
        List<T> list = getByValue(enumClass, value);
        if (list.isEmpty()) {
            return value;
        }
        return list.stream().map(IDictItem::label).collect(Collectors.joining(SPLIT_SYMBOL));
    }

    /**
     * 通过 label 来获取 value
     *
     * @param enumClass 枚举类
     * @param label 枚举代码
     * @param <T> 模板类型
     * @return 如果 value为空
     */
    static <T extends IDictItem> String getValueByLabel(Class<T> enumClass, String label) {
        if (label == null) {
            return "";
        }
        List<T> list = getByValue(enumClass, label);
        if (list.isEmpty()) {
            return label;
        }
        return list.stream().map(IDictItem::value).collect(Collectors.joining(SPLIT_SYMBOL));
    }

}
