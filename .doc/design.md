# design

## 需求

1. 前端获取数据字典类型
2. 后端获取数据字典类型

3. item里面本来默认有值, 但之后更新了 type 变得没有值了.


## 多数据格式

对于不同类型的字段数据字典的方案: 例如一个字段代表一个数据字典, 但有的字段是同时存入了多个数据字典code, 中间使用`,`分开, 

除此之外也有其它的类型, 区分这种类型字段方案

方案一: 通过使枚举实现不同的接口, 

例如可以为`一个字段同时存入多个数据字典` 这种情况新定义一种接口 `IDictMultiItem`, 通过使枚举实现 `IDictMultiItem` 或 `IDictItem` 来区分不同的字段存储类型

优点: 方便字段和枚举的统一管理.

缺点: 增加了复杂度, 同时为枚举添加了字段的属性

方案二: 依然使用 `IDictItem`, 增加接口工具类, 仅仅为枚举提供方法.

优点: 简单

缺点: 无法通过枚举判断字段存入的是什么类型