package com.github.cosycode.codedict.dynamic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <b>Description : </b> 缓存字典操作类
 * <p>
 * <b>created in </b> 2019/12/13
 *
 * @author CPF
 * @since 1.2
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictHandler {

    private static DictDbIoHandle dbIoHandle;

    private static DictCacheIoHandle cacheIoHandle;

    public static void register(DictDbIoHandle dbIoHandle) {
        register(dbIoHandle, null);
    }

    public static void register(DictDbIoHandle dbIoHandle, DictCacheIoHandle cacheIoHandle) {
        DictHandler.dbIoHandle = dbIoHandle;
        if (cacheIoHandle != null) {
            DictHandler.cacheIoHandle = cacheIoHandle;
        }
        initDictCachePool();
    }

    /**
     * 初始化代码项池,
     * 初始时查出所有代码项, 将其put到池里面
     */
    private static void initDictCachePool() {
        Map<String, DictTypeBean> map;

        // 通过时间判断redis里面数据是否完整;
        if (cacheIoHandle != null && cacheIoHandle.existDictData()) {
            map = cacheIoHandle.readAllFromCache();
        } else {
            if (dbIoHandle == null) {
                return;
            }
            map = dbIoHandle.queryAllDataFromDb();
            // 再次判断redis里面数据情况, 考虑加锁情况
            if (cacheIoHandle != null) {
                // 1. 如果没有数据, 则塞入数据
                // 2. 如果正在被塞入数据, 则可以开额外线程判断redis数据是否完整
                cacheIoHandle.writeAllToCache(map);
            }
        }
        if (map == null || map.isEmpty()) {
            // log.warn("未获取到数据");
        }
        // 塞入pool
        ConfigurableDictPool.putFieldTypeMap(map);
    }

    public static DictTypeBean getDictTypeBean(String fieldKey) {
        // 如果不能从数据库中获取数据
        if (dbIoHandle == null) {
            return ConfigurableDictPool.getTypeByFieldKey(fieldKey);
        }

        // 从pool里面获取, 获取到则返回
        DictTypeBean type = ConfigurableDictPool.getTypeByFieldKey(fieldKey);
        if (type != null) {
            return type;
        }
        // 如果缓存可用
        if (cacheIoHandle != null) {
            // 从redis里面获取, 获取成功后存入本地pool, 再从pool中获取
            type = cacheIoHandle.readOneFromCache(fieldKey);
            if (type != null) {
                ConfigurableDictPool.putField8Type(fieldKey, type);
                return type;
            }
        }

        // 从数据库中获取
        if (dbIoHandle != null) {
            type = dbIoHandle.queryOneDataFromDb(fieldKey);
            if (type != null) {
                if (cacheIoHandle != null) {
                    cacheIoHandle.writeOneToCache(type);
                }
                ConfigurableDictPool.putField8Type(fieldKey, type);
                return type;
            }
        }

        return new DictTypeBean("");
    }

}
