package com.github.cosycode.codedict.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b> 静态字典池, 存放代码中原本存在的字典内容
 * <p>
 * <b>created in </b> 2019/12/13 19:26
 *
 * @author CPF
 * @since 1.0
 **/
class StaticDictPool {

    /**
     * 用于存储字典数据
     */
    private static final Map<IDictItem, DictItemBean> dictItemMap = new ConcurrentHashMap<>();

    private StaticDictPool() {
    }

    /**
     * 往 map 中添加代码项
     */
    public static void putDictItem(IDictItem iCodeItem, String value, String label) {
        dictItemMap.put(iCodeItem, DictItemBean.of(value, label));
    }

    /**
     * 获取静态数据
     */
    public static DictItemBean getDictItem(IDictItem iDictItem) {
        return dictItemMap.get(iDictItem);
    }

}
