package com.github.cosycode.codedict.core;

import com.github.cosycode.codedict.util.StringUtils;

/**
 * <b>Description : </b> 数据字典枚举接口
 * <p>
 * <b>created in </b> 2020/3/16
 *
 * @author CPF
 * @since 1.0
 **/
public interface IDictItem {

    default void putItemBean(String value, String label) {
        StaticDictPool.putDictItem(this, value, label);
    }

    default DictItemBean getItemBean() {
        return StaticDictPool.getDictItem(this);
    }

    default String value() {
        return getItemBean().getValue();
    }

    default String label() {
        return getItemBean().getLabel();
    }

    default boolean isValue(String value) {
        return StringUtils.isNotBlank(value) && value.endsWith(value());
    }

}
