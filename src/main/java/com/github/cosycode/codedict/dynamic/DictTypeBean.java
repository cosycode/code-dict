package com.github.cosycode.codedict.dynamic;

import com.github.cosycode.codedict.core.DictItemBean;
import lombok.Getter;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b> 用于缓存字典类型数据
 * <p>
 * <b>created in </b> 2019/12/13
 *
 * @author CPF
 * @since 1.2
 **/
public class DictTypeBean {

    public static final DictTypeBean NULL_OBJECT = new DictTypeBean("NULL_OBJECT");
    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    @Getter
    private final String typeKey;
    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    @Getter
    private final Map<String, DictItemBean> itemMap;

    public DictTypeBean(String typeKey) {
        this(typeKey, new ConcurrentHashMap<>());
    }
    public DictTypeBean(String typeKey, Map<String, DictItemBean> itemMap) {
        this.typeKey = typeKey;
        this.itemMap = itemMap;
    }

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
