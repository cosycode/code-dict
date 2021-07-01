package com.github.cosycode.codedict.dynamic;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b> 缓存抽象类, 便于配置从数据库中获取字典数据
 * <p>
 * <b>created in </b> 2019/12/16
 *
 * @author CPF
 * @since 1.2
 **/
public abstract class DictDbIoHandle {

    public abstract Map<String, DictTypeBean> queryAllDataFromDb();

    public abstract Map<String, DictTypeBean> queryPartDataFromDb(List<String> fieldKeyList);

    /**
     * @param fieldKey 字段标识
     */
    public abstract DictTypeBean queryOneDataFromDb(String fieldKey);

}
