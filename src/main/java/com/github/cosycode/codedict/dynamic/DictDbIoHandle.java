package com.github.cosycode.codedict.dynamic;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/16 14:06
 **/
public abstract class DictDbIoHandle {

    public abstract Map<String, DictTypeBean> queryAllDataFromDb();

    public abstract Map<String, DictTypeBean> queryPartDataFromDb(List<String> fieldKeyList);

    /**
     * @param fieldKey 字段标识
     */
    public abstract DictTypeBean queryOneDataFromDb(String fieldKey);

}
