package com.github.codedict.core;


import com.github.codedict.util.StringUtils;

public interface IDictItem {

    /**
     * 通过value来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param value 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配value的枚举对象
     */
    static <T extends IDictItem> T getByValue(Class<T> enumClass, String value) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用value进行循环比较，获取对应的枚举
            if (value.equals(each.value())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过label来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param label 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配label的枚举对象
     */
    static <T extends IDictItem> T getByLabel(Class<T> enumClass, String label) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用value进行循环比较，获取对应的枚举
            if (label.trim().equals(each.label())) {
                return each;
            }
        }
        return null;
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
        IDictItem byValue = getByValue(enumClass, value);
        if (null == byValue) {
            return value;
        }
        return byValue.label();
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
        label = label.trim();
        IDictItem byValue = getByLabel(enumClass, label);
        if (null == byValue) {
            return label;
        }
        return byValue.value();
    }

    String name();

    default String value() {
        return getItemBean().getValue();
    }

    default String label() {
        return getItemBean().getLabel();
    }

    default DictItemBean getItemBean(){
        return StaticDictPool.getDictItem(this);
    }

    default boolean isValue(String value) {
        return StringUtils.isNotBlank(value) && value.endsWith(value());
    }

}
