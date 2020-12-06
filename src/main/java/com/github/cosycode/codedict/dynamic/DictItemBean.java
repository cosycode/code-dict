package com.github.cosycode.codedict.dynamic;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/13 10:43
 **/
@Data
@NoArgsConstructor
public class DictItemBean {

    public static DictItemBean of(String value, String label) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        return dictItemBean;
    }

    /**
     * 父节点code
     */
    private String parValue;

    private String value;

    private String label;

    /**
     * 排序
     */
    private int ord;

    /**
     * 是否可用
     */
    private boolean enable;

}
