package com.github.codedict.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b> 静态字典池, 存放代码中原本存在的字典内容
 *
 * @author CPF
 * @date 2019/12/13 19:26
 **/
public class StaticDictPool {

    private StaticDictPool() {
    }

    /**
     * 用于存储字典数据
     */
    private static final Map<IDictItem, DictItemBean> dictItemMap = new ConcurrentHashMap<>();

    /**
     * 往 map 中添加代码项
     */
    public static void putDictItem(IDictItem iCodeItem, String value, String label) {
        dictItemMap.put(iCodeItem, DictItemBean.of(value, label));
    }

    /**
     * 获取静态数据
     */
    static DictItemBean getDictItem(IDictItem iCodeItem) {
        return dictItemMap.get(iCodeItem);
    }

}
