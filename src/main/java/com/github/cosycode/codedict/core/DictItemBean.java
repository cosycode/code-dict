package com.github.cosycode.codedict.core;

/**
 * <b>Description : </b> 用于存放数据字典的单个项对应的Bean
 * <p>
 * <b>created in </b> 2019/12/13 10:43
 *
 * @author CPF
 * @since 1.0
 **/
public class DictItemBean {

    private String value;
    private String label;

    public static DictItemBean of(String value, String label) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        return dictItemBean;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
