package com.github.cosycode.codedict.dynamic;

import com.github.cosycode.codedict.core.DictItemBean;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2019/12/13
 *
 * @author CPF
 * @since 1.2
 **/
@NoArgsConstructor
public class DyDictItemBean extends DictItemBean {

    /**
     * 父节点code
     */
    private String parValue;
    /**
     * 排序
     */
    private int ord;
    /**
     * 是否可用
     */
    private boolean enable;

    public static DyDictItemBean of(String value, String label) {
        final DyDictItemBean dictItemBean = new DyDictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        return dictItemBean;
    }

    public String getParValue() {
        return parValue;
    }

    public void setParValue(String parValue) {
        this.parValue = parValue;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
