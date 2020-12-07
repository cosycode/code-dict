# code-dict

README [ENGLISH](./README.en.md) | 中文

## index

* [Project Description](#Project Description)
* [Design Ideas](#Design Ideas)
* [Brief Description of Benefits](#Brief Description of Benefits)
* [Integration Methods](#Integration Methods)
* [Usage](#Usage)
* [Follow up plan(if I have time)](#Follow up plan)

## Project Description

A simple general-purpose data dictionary framework

Use enumeration to manage the complexity of data dictionaries in a project. Each data dictionary interface has an enumeration class that corresponds to the fields of the entity class with the data dictionary, involving the retrieval of the data dictionary without the use of static variables or magic values, and using enumerations.

By using the enumeration as a proxy entry point, the real data is stored in the data dictionary pool, greatly reducing the enumeration code.

## Design Ideas

0. **Reduces the code of the enumeration class, uses the enumeration as a proxy entry point, and extracts the methods and fields from the enumeration into a unified class**.

## Brief Description of Benefits

1. **Simple and easy to use**, when you use it to a field of a table which contains data dictionary, you can easily get the information of the field according to the IDE code hint. 2.

2. **Proof and easy to maintain**, since it uses enumeration, it is not easy to make mistake when using it, and if you change the value of the dictionary table, you only need to change the corresponding enumeration class. 3.

3. **Unify format, add functionality**, generally enumeration will need to use some unified methods, such as get label by value, get value by label, or multiple choice field value conversion. 4. **Convenient to add additional logic to a single field**.
   
4. **Convenient to add additional logic to a single field**, since it uses enumeration classes, it is easy to use and manage the field's processing logic by simply adding methods to the field's corresponding enumeration classes.

Translated with www.DeepL.com/Translator (free version)

> principle: [Manage and use dictionary tables with ease and elegance using enumerations](.doc/principle.md)

## Integration Methods

> [sonatype-Repository](https://search.maven.org/artifact/com.github.cosycode/code-dict/1.1/jar)

1. Apache Maven

    ```xml
    <dependency>
      <groupId>com.github.cosycode</groupId>
      <artifactId>code-dict</artifactId>
      <version>1.1</version>
    </dependency>
    ```

2. Gradle Groovy DSL

    ```yml
    implementation 'com.github.cpfniliu:code-dict:1.0'
    ```
   
## Usage

If you have two tables (simple, some non-empty, length and so on), both have `gender` and `state`, the `gender` dictionary entry is the same, but the `state` dictionary entry is different

1. Table: Student

    | field | type    | dictionary                                              |
    | ------------- | ------- | --------------------------------------------------- |
    | stuNo         | INTEGER |                                                     |
    | name          | VARCHAR |                                                     |
    | gender        | VARCHAR | {man:1, woman:2}                                     |
    | state         | VARCHAR | {Failure to report:10, Enrollment:20, Graduation:30, Completion:40, Dropout:50, Withdrawal:60, Expulsion:70} |

2. Table: Teacher

    | field | type    | dictionary                        |
    | ------------- | ------- | --------------------------------- |
    | teaNo         | INTEGER |                                   |
    | name          | VARCHAR |                                   |
    | gender        | VARCHAR | {man:1, woman:2}                                     |
    | state         | VARCHAR | {Failure to report:10, In-service:20, Separation:30, Dismissal:40} |

Then you can create a data dictionary interface for each of the two tables (the interface is used because it doesn't need to have a member attribute), which is used to manage the data dictionary. The relevant code is as follows

1. Student Interface

    ```java
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
    ```

2. Teacher Interface

    ```java
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
    ```

> Standard Formats for Data Dictionary Interfaces:
>
> The interface name is `Dic` + table name.
> There are multiple enumeration classes inside the interface, each of which corresponds to a field in the table that has a data dictionary. The name of the enumeration is a humped string of field names.
> Each enumeration implements the `IDictItem` interface, where each dictionary item of the enumeration corresponds to each data dictionary item of the field.

## Benefits of using enumerations to manage data dictionaries

1. **Easy to use**, when used with a field of a table that contains a data dictionary, the information of the field can be easily retrieved according to the IDE code hints.

    ![枚举的好处-1](./.src/enum-merit-1.gif)
    
2. **prevent error, easy to maintain**, because it is an enumeration, it is not easy to make mistake, and if you change the value of the dictionary table, you only need to change the corresponding enumeration class. 3.

3. **Unify the format, add functionality**, generally enumeration will need to use some unified methods, such as get label by value, get value by label, or multiple choice field value conversion, similar to this unified methods can be added or deleted in `DictItems` to adjust the overall functionality. .

   ```java
   import com.github.cosycode.codedict.core.DictItems;
   
   public class Test {
   
       public static void main(String[] args) {
           // Get the data dictionary code of the Student table whose gender is male.
           DicStudent.Gender.man.value();
           // Get Teacher table status The data dictionary code that represents the exit.
           DicTeacher.State.resigned.value();
           // Get Teacher table status The text display label that represents the exit.
           DicTeacher.State.resigned.label();
       }
   
       @org.junit.Test
       public void iDictItemTest() {
           // If one of the values in the Teacher table status is 30, determine if the current value represents graduation.
           String teaState = "30";
           DicTeacher.State.resigned.isValue(teaState); // true
           DicTeacher.State.notReported.isValue(teaState); // false
   
           // For the Status field of the Teacher table, get the corresponding data dictionary code from the text label: "Active".
           DictItems.getValueByLabel(DicTeacher.State.class, "incumbent"); // return: "20"
           // For the status field of the Teacher table, get the corresponding text label from the data dictionary code.
           DictItems.getLabelByValue(DicTeacher.State.class, "20"); // Return: "In-service"
           // For the status field of the Teacher table, get the corresponding enumeration item from the text label.
           DictItems.getByLabel(DicTeacher.State.class, "work"); // Return: DicTeacher.State.work
           // For the status field of the Teacher table, get the corresponding enumeration from the data dictionary code.
           DictItems.getByValue(DicTeacher.State.class, "20"); // Return: DicTeacher.State.work
       }
   
   }
   ```

4. **Convenient to add additional logic to a single field**, because of the enumeration classes used, it is easy to use and manage the field's processing logic by simply adding methods to the corresponding enumeration classes.

    For example, if you have a field with a data dictionary and you need to do something separate for that field, and it's not convenient to write that logic in code or in a separate class, you can write that logic in the corresponding enumeration class.

    Here's a simple example: For example, in the Teacher class's Gender field above, the data dictionary used internally is `("1", "male"),("2", "female")`, but due to some circumstances, the push to the outside needs to be in the format of `("male", "male"),("female", "female")`, In that case, you can add two methods to the enumeration and it will be easy to solve the problem.

    ```java
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
    ```

## Follow up plan

1. Extract four separate tables to maintain table information, field information, enumeration types, and enumeration items.
2. create the add/drop maintenance pages for the above four tables. 
3. Separate different IDictItem for different classes, or add common tool classes for different enumeration types. 
4. Configurable data dictionary. 
5. Generate enumerations from tables with one click.
6. support for multi-level data dictionary

