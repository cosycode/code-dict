package com.github.cosycode.codedict.dynamic;

import lombok.Getter;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b> 用于缓存字典类型数据
 *
 * @author CPF
 * @date 2019/12/13 15:06
 **/
public class DictTypeBean {

    public static final DictTypeBean NULL_OBJECT = new DictTypeBean("NULL_OBJECT");

    public DictTypeBean(String typeKey) {
        this(typeKey, new ConcurrentHashMap<>());
    }

    public DictTypeBean(String typeKey, Map<String, DictItemBean> itemMap) {
        this.typeKey = typeKey;
        this.itemMap = itemMap;
    }

    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    @Getter
    private String typeKey;
    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    private Map<String, DictItemBean> itemMap;

    public void putDictItemBean(IDyDictItem dictItem, DictItemBean dictItemBean) {
        itemMap.put(dictItem.name(), dictItemBean);
    }

    public DictItemBean getDictItemBean(IDyDictItem dictItem) {
        return itemMap.get(dictItem.name());
    }

    public Collection<DictItemBean> listItem() {
        return itemMap.values();
    }

    public boolean isEmpty() {
        return this.equals(NULL_OBJECT);
    }

}
