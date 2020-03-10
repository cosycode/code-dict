package com.github.cpf.item;

import com.github.codedict.core.IDictItem;
import com.github.codedict.core.StaticDictPool;

/**
 * <b>Description : </b> 通知表
 *
 * @author CPF
 * @date 2019/3/4 15:50
 **/
public interface DicNotes {

    /**
     * <b>Description : </b> 通知类型
     *
     * @author CPF
     * @date 2019/10/20 15:50
     **/
    enum NotesType implements IDictItem {

        SYSTEM("S", "系统通知"),
        BIND("B", "绑定通知"),
        ACTIVE("A", "激活通知"),
        GROUP("G", "小组通知"),
        FRIEND("F", "好友");

        NotesType(String code, String text) {
            StaticDictPool.putDictItem(this, code, text);
        }
    }

}
