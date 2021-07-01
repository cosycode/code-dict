package com.github.cosycode.codedict.dynamic;

import java.util.Map;

/**
 * <b>Description : </b> 缓存抽象类, 便于配置从缓存中获取字典数据
 * <p>
 * <b>created in </b> 2019/12/16
 *
 * @author CPF
 * @since 1.2
 **/
public abstract class DictCacheIoHandle {

    public abstract Map<String, DictTypeBean> readAllFromCache();

    public abstract DictTypeBean readOneFromCache(String fieldKey);

    public abstract void writeAllToCache(Map<String, DictTypeBean> dataListMap);

    /**
     * 写数据到缓存
     * 分布式锁, 额外线程.
     */
    public void writeOneToCache(DictTypeBean data) {
        new Thread().start();
    }

    public abstract void doWriteOneToCache(DictTypeBean data);

    /**
     * 判断redis里面数据是否存在
     * 1. redis里面没有相关键    : return false
     * 2. redis正在被其它线程塞数据 : 等待一秒, 再次验证, 若依然如此, 返回 false
     * 3. redis里面有完整数据 : return true
     * 4. redis里面有数据, 但是数据不全 return true// 该结果应该无法验证
     * 5. redis无法联通  : return false
     *
     * @return true表示可以取数据, false表示不能取数据
     */
    public abstract boolean existDictData(String... tagList);

}
