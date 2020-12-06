package com.github.cosycode.stat;

import com.github.cosycode.codedict.core.IDictItem;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/12 16:50
 **/
public interface DicCommon {

    enum State implements IDictItem {
        enable("y", "启用"),
        disable("n", "禁用");

        State(String value, String label) {
            putItemBean(value, label);
        }
    }

}
