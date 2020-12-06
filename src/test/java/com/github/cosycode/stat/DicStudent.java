package com.github.cosycode.stat;

import com.github.cosycode.codedict.core.IDictItem;

/**
 * <b>Description : </b> 学生数据字典接口
 *
 * @author CPF
 * @date 2019/12/13 10:43
 **/
public interface DicStudent {

    /**
     * 性别 : {男:1, 女:2}
     */
    enum Gender implements IDictItem {

        man("1", "男"), woman("2", "女");

        Gender(String value, String label) {
            putItemBean(value, label);
        }
    }

    /**
     * 状态
     */
    enum State implements IDictItem {

        notReported("10", "未报到"),
        reading("20", "在读"),
        graduation("30", "毕业"),
        defamation("40", "肄业"),
        completion ("50", "肄业"),
        withdrawal("60", "退学"),
        expulsion("70", "开除");

        State(String value, String label) {
            putItemBean(value, label);
        }
    }
}
