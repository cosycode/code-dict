package com.github.cosycode.codedict.dynamic;

import com.github.cosycode.codedict.core.DictItemBean;
import com.github.cosycode.codedict.util.StringUtils;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2020/3/16
 *
 * @author CPF
 * @since 1.2
 **/
public interface IDyDictItem {

    String name();

    /**
     * // FIXME 如果要找到 fieldKey 则需要 字段 <---> 代码类型 一一对应
     *
     * @return 当前代码项 所在代码类型 对应的 字段标识
     */
    default String fieldKey() {
        return name();
    }

    /**
     * @return 当前代码项 所在代码类型 标识
     */
    default String typeKey() {
        return name();
    }

    default String value() {
        return getItemBean().getValue();
    }

    default String label() {
        return getItemBean().getLabel();
    }

    default DictItemBean getItemBean() {
        return ConfigurableDictPool.getItem(this);
    }

    default void putItemBean(String value, String label) {
        ConfigurableDictPool.putItem(this, DictItemBean.of(value, label));
    }

    default boolean isValue(String value) {
        return StringUtils.isNotBlank(value) && value.endsWith(value());
    }

}
