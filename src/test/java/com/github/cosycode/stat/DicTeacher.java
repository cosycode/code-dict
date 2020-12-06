package com.github.cosycode.stat;

import com.github.cosycode.codedict.core.IDictItem;

/**
 * <b>Description : </b> 教师数据字典接口
 *
 * @author CPF
 * @date 2019/12/13 10:43
 **/
public interface DicTeacher {

    /**
     * 性别 : {男:1, 女:2}
     */
    enum Gender implements IDictItem {

        man("1", "男"), woman("2", "女");

        Gender(String value, String label) {
            putItemBean(value, label);
        }

        public String innerToOuter(String val) {
            if (man.isValue(val)) {
                return "male";
            }
            if (woman.isValue(val)) {
                return "female";
            }
            throw new RuntimeException("转换出现异常");
        }

        String outerToInner(String val) {
            if ("male".equals(val)) {
                return man.value();
            }
            if ("female".equals(val)) {
                return woman.value();
            }
            throw new RuntimeException("转换出现异常");
        }
    }

    /**
     * 状态
     */
    enum State implements IDictItem {

        notReported("10", "未报到"),
        work("20", "在职"),
        resigned("30", "离职"),
        expelled("40", "开除");

        State(String value, String label) {
            putItemBean(value, label);
        }
    }
}
