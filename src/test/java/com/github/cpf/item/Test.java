package com.github.cpf.item;

import com.github.codedict.core.IDictItem;

public class Test {

    public static void main(String[] args) {
        // 获取 Student 表 性别 为 男性的 数据字典代码
        DicStudent.Gender.man.value();
        // 获取 Teacher 表 状态 表示离职的 数据字典代码
        DicTeacher.State.resigned.value();
        // 获取 Teacher 表 状态 表示离职的 文本显示标签
        DicTeacher.State.resigned.label();
    }


    @org.junit.Test
    public void iDictItemTest() {
        // 假如 Teacher 表 状态 的一个value是30, 需要判断当前值是否代表毕业
        String teaState = "30";
        DicTeacher.State.resigned.isValue(teaState);  //  true
        DicTeacher.State.notReported.isValue(teaState);  //  false

        // 针对 Teacher 表 的 状态 字段,  通过 文本标签:"在职" 获取对应的 数据字典代码
        IDictItem.getValueByLabel(DicTeacher.State.class, "在职");   // 返回: "20"
        // 针对 Teacher 表 的 状态 字段,  通过 数据字典代码 获取对应的 文本标签
        IDictItem.getLabelByValue(DicTeacher.State.class, "20");   // 返回: "在职"
        // 针对 Teacher 表 的 状态 字段,  通过 文本标签 获取对应的 枚举项
        IDictItem.getByLabel(DicTeacher.State.class, "在职");   // 返回: DicTeacher.State.work
        // 针对 Teacher 表 的 状态 字段,  通过 数据字典代码 获取对应的 枚举项
        IDictItem.getByValue(DicTeacher.State.class, "20");   // 返回: DicTeacher.State.work
    }

}
