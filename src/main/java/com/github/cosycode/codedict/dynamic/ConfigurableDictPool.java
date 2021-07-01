package com.github.cosycode.codedict.dynamic;

import com.github.cosycode.codedict.util.StringUtils;
import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2019/12/13
 *
 * @author CPF
 * @since 1.2
 **/
class ConfigurableDictPool {

    /**
     * 用于存储字典数据(String 对应字段标识)
     */
    private static final Map<String, DictTypeBean> typeBeanMap = new ConcurrentHashMap<>();
    /**
     * field 和 type 的映射
     */
    private static final Map<String, String> field8Type = new ConcurrentHashMap<>();

    private ConfigurableDictPool() {
    }

    /**
     * map中单例, 防止 typeBeanMap 中建立多个相同类型的 DictTypeBean
     * 用于静态默认数据初始化
     *
     * @param iDyDictItem dictItem
     */
    private static @NonNull DictTypeBean getFromMap(IDyDictItem iDyDictItem) {
        String fieldKey = iDyDictItem.fieldKey();
        String typeKey = iDyDictItem.typeKey();
        // 确保typeBeanMap中有相关值
        DictTypeBean dictTypeBean = typeBeanMap.get(typeKey);
        if (dictTypeBean == null) {
            dictTypeBean = typeBeanMap.computeIfAbsent(fieldKey, key -> new DictTypeBean(typeKey));
        }
        // 添加 field -> typeBean 映射
        if (!field8Type.containsKey(fieldKey)) {
            field8Type.put(fieldKey, typeKey);
        }
        return dictTypeBean;
    }

    /**
     * @param map
     */
    public static void putFieldTypeMap(Map<String, DictTypeBean> map) {
        map.forEach(ConfigurableDictPool::putField8Type);
    }

    public static void putField8Type(String fieldKey, DictTypeBean typeBean) {
        field8Type.put(fieldKey, typeBean.getTypeKey());
        typeBeanMap.put(typeBean.getTypeKey(), typeBean);
    }

    public static void putDictType(DictTypeBean typeBean) {
        typeBeanMap.put(typeBean.getTypeKey(), typeBean);
    }

    public static void putField8TypeMapping(String fieldKey, String dictType) {
        field8Type.put(fieldKey, dictType);
    }

    public static DictTypeBean getTypeByTypeKey(String typeKey) {
        return typeBeanMap.get(typeKey);
    }

    public static DictTypeBean getTypeByFieldKey(String fieldKey) {
        String typeKey = field8Type.get(fieldKey);
        if (StringUtils.isBlank(typeKey)) {
            return null;
        }
        return typeBeanMap.get(typeKey);
    }

    /**
     * 往 map 中添加代码项
     */
    public static void putItem(IDyDictItem iDyDictItem, DyDictItemBean dictItemBean) {
        DictTypeBean dictTypeBean = getFromMap(iDyDictItem);
        dictTypeBean.putDictItemBean(iDyDictItem, dictItemBean);
    }

    public static DyDictItemBean getItem(IDyDictItem iDyDictItem) {
        DictTypeBean typeByTypeKey = getTypeByTypeKey(iDyDictItem.typeKey());
        return typeByTypeKey == null ? null : typeByTypeKey.getDictItemBean(iDyDictItem);
    }

}
