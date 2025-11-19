# 10-Reflection(反射)

> 鸣谢：黑马程序员
>
> ![image-20251110175250685](images/image-20251110175250685.png)

> [!TIP]
>
> Previous Chapter(上一章)：09-Network Communication(网络通信)
>
> + [Markdown](../09-Network Communication(网络通信)/09-Network Communication(网络通信).md)
> + [PDF](../09-Network Communication(网络通信)/09-Network Communication(网络通信).pdf)
> + [HTML](../09-Network Communication(网络通信)/09-Network Communication(网络通信).html)
>
> Next Chapter(下一章)：11-Annotation(注解)
>
> + [Markdown](../11-Annotation(注解)/11-Annotation(注解).md)
> + [PDF](../11-Annotation(注解)/11-Annotation(注解).pdf)
> + [HTML](../11-Annotation(注解)/11-Annotation(注解).html)



[TOC]

## 一、概述

![image-20251117132112097](images/image-20251117132112097.png)



---



## 二、操作步骤

### 1.加载类，获取类的字节码：`Class`对象

#### 1.1 获取`Class`对象的3种方式

+ `Class c1 = 目标类名.class`

+ 调用`Class`类提供的静态方法：`public static Class forName(String package)`

  + `Class c2 = Class.forName(目标类的完整类名，即包括所在包名)`

+ 调用`Object`类提供的方法：`public Class getClass()`

  + `Class c3 = 目标类的实例对象.getClass()`

    

#### 1.2 代码演示

首先在模块下新建一个包`reflection`，然后创建一个学生类`Student`和一个测试类`Test1`。

+ `Student`:

```java
package reflection;

import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private double height;

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // 省略getter&setter
}
```

+ `Test1`:

```java
package reflection;

public class Test1 {
    public static void main(String[] args) throws Exception {
        Class c1 = Student.class;
        System.out.println(c1.getName());// 完整类名，包括前面的包名
        System.out.println(c1.getSimpleName());// 简名：Student

        Class c2 = Class.forName("reflection.Student");
        if (c2.equals(c1)) {
            System.out.println("c2 == c1");
        }

        Student s = new Student();
        Class c3 = s.getClass();
        if (c3.equals(c1)) {
            System.out.println("c3 == c1");
        }
    }
}
```

+ 控制台输出：

  <img src="images/image-20251117134943586.png" alt="image-20251117134943586" style="zoom:67%;" />



---



### 2.获取类的构造器：`Constructor`对象

#### 2.1 常用方法 

##### P1 `Class`类提供的方法

| 序号 | 方法                                                         | 说明                     |
| ---- | ------------------------------------------------------------ | ------------------------ |
| 01   | `Constructor<?>[] getConstructors()`                         | 获取全部`public`构造器。 |
| 02   | `Constructor<?>[] getDeclaredConstructors()`                 | 获取全部构造器。         |
| 03   | `Constructor<?>[] getConstructor(Class<?>... parameterTypes)` | 获取某个`public`构造器。 |
| 04   | `Constructor<?>[] getDeclaredConstructor(Class<?>... parameterTypes)` | 获取某个构造器。         |

##### P2 `Constructor`类提供的方法

| 序号 | 方法                                     | 说明                                                         |
| ---- | ---------------------------------------- | ------------------------------------------------------------ |
| 01   | `Object newInstance(Object... initargs)` | 调用此构造器对象表示的构造器，传入参数，完成对象的初始化并返回。 |
| 02   | `void setAccessible(boolean flag)`       | 当`flag`设置为`true`时，表示禁止编译器检查访问权限，即**暴力反射**。 |



---



#### 2.2 代码演示

在包`reflection`下继续创建一个猫类`Cat`和一个测试类`Test2`。

+ `Cat`:

  ```java
  package reflection;
  
  public class Cat {
      private String name;
      private int age;
  
      public Cat() {
          System.out.println("no-param constructor is executed");
      }
  
      private Cat(String name) {
          this.name = name;
          System.out.println("private Cat(String name) is executed");
      }
  
      private Cat(int age) {
          System.out.println("private Cat(int age) is executed");
          this.age = age;
      }
  
      public Cat(String name, int age) {
          this.name = name;
          this.age = age;
          System.out.println("full-params constructor is executed");
      }
      
      @Override
      public String toString() {
          return "Cat{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
      
      // 省略getter&setter
  }
  ```

+ `Test2`:

  ```java
  package reflection;
  
  import java.lang.reflect.Constructor;
  import java.util.Arrays;
  
  public class Test2 {
      public static void main(String[] args) throws Exception {
          Class c = Cat.class;
  
          System.out.println("========All Public Constructors========");
          Arrays.stream(c.getConstructors()).forEach(constructor -> {
              System.out.println(constructor.toString() + " ==> " + constructor.getParameterCount() + " params");
          });
          System.out.println();
  
          System.out.println("========All Constructors========");
          Arrays.stream(c.getDeclaredConstructors()).forEach(constructor -> {
              System.out.println(constructor.toString() + " ==> " + constructor.getParameterCount() + " params");
          });
          System.out.println();
  
          Constructor noParamConstructor = c.getConstructor();
          System.out.println("no-param constructor: " + noParamConstructor);
          Constructor fullParamsConstructor = c.getDeclaredConstructor(String.class, int.class);
          System.out.println("full-params constructor: " + fullParamsConstructor);
      }
  }
  ```

+ 控制台输出：

  <img src="images/image-20251117142201586.png" alt="image-20251117142201586" style="zoom: 80%;" />



---



#### 2.3 获取构造器的作用：初始化对象并返回

```java
package reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Class c = Cat.class;

        Constructor constructor = c.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);// 允许访问私有构造器，会破坏封装性
        Cat cat = (Cat) constructor.newInstance("狸花猫");
        System.out.println(cat);
    }
}
```

控制台输出：

<img src="images/image-20251117143943757.png" alt="image-20251117143943757" style="zoom: 80%;" />



---



### 3.获取类的成员变量：`Field`对象

#### 3.1 常用方法

##### P1 `Class`类提供的方法

| 序号 | 方法                                  | 说明                       |
| ---- | ------------------------------------- | -------------------------- |
| 01   | `Field[] getFields()`                 | 获取全部`public`成员变量。 |
| 02   | `Field[] getDeclaredFields()`         | 获取全部成员变量。         |
| 03   | `Field getField(String name)`         | 获取某个`public`成员变量。 |
| 04   | `Field getDeclaredField(String name)` | 获取某个成员变量。         |

##### P2 `Field`类提供的方法

| 序号 | 方法                                 | 说明                                                         |
| ---- | ------------------------------------ | ------------------------------------------------------------ |
| 01   | `void set(Object obj, Object value)` | 赋值。                                                       |
| 02   | `Object get(Object obj)`             | 取值。                                                       |
| 03   | `void setAccessible(boolean flag)`   | 当`flag`设置为`true`时，表示禁止编译器检查访问权限，即**暴力反射**。 |



---



#### 3.2 代码演示

+ `Cat`:

  ```java
  package reflection;
  
  public class Cat {
      public static int a;
      public static final String COUNTRY = "CHINA";
      private String name;
      private int age;
  
      public Cat() {
          System.out.println("no-param constructor is executed");
      }
  
      private Cat(String name) {
          this.name = name;
          System.out.println("private Cat(String name) is executed");
      }
  
      private Cat(int age) {
          System.out.println("private Cat(int age) is executed");
          this.age = age;
      }
  
      public Cat(String name, int age) {
          this.name = name;
          this.age = age;
          System.out.println("full-params constructor is executed");
      }
  
      @Override
      public String toString() {
          return "Cat{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  
      // 省略getter&setter
  }
  ```

+ `Test3`:

  ```java
  package reflection;
  
  import java.lang.reflect.Field;
  import java.util.Arrays;
  
  public class Test3 {
      public static void main(String[] args) throws Exception {
          Class c = Cat.class;
  
          System.out.println("========All Fields========");
          Arrays.stream(c.getDeclaredFields()).forEach(f -> {
              System.out.println(f.getName() + " ==> " + f.getType());
          });
          System.out.println();
  
          Field fName = c.getDeclaredField("name");
          System.out.println(fName.getName() + " ==> " + fName.getType());
      }
  }
  ```

+ 控制台输出：

  <img src="images/image-20251117145938400.png" alt="image-20251117145938400" style="zoom:80%;" />



---



#### 3.3 获取成员变量的作用：赋值与取值

```java
package reflection;

import java.lang.reflect.Field;

public class Test3 {
    public static void main(String[] args) throws Exception {
        Class c = Cat.class;

        Field fName = c.getDeclaredField("name");
        System.out.println(fName.getName() + " ==> " + fName.getType());
        fName.setAccessible(true);
        Cat cat = new Cat();

        fName.set(cat, "三花猫");
        System.out.println("赋值后：" + cat);
        String name = (String) fName.get(cat);// 强转
        System.out.println("取值：" + name);

    }
}
```

控制台输出：

<img src="images/image-20251117151148745.png" alt="image-20251117151148745" style="zoom:80%;" />



---



### 4.获取类的成员方法：`Method`对象

#### 4.1 常用方法

##### P1 `Class`类提供的方法

| 序号 | 方法                                                         | 说明                       |
| ---- | ------------------------------------------------------------ | -------------------------- |
| 01   | `Method[] getMethods()`                                      | 获取全部`public`成员方法。 |
| 02   | `Method[] getDeclaredMethods()`                              | 获取全部成员方法。         |
| 03   | `Method getMethod(String name, Class<?>... parameterTypes)`  | 获取某个`public`成员方法。 |
| 04   | `Method getDeclaredMethod(String name, Class<?>... parameterTypes)` | 获取某个成员方法。         |

##### P2 `Method`类提供的方法

| 序号 | 方法                                        | 说明                                                         |
| ---- | ------------------------------------------- | ------------------------------------------------------------ |
| 01   | `Object invoke(Object obj, Object... args)` | 执行目标对象的指定方法。                                     |
| 02   | `void setAccessible(boolean flag)`          | 当`flag`设置为`true`时，表示禁止编译器检查访问权限，即**暴力反射**。 |



---



#### 4.2 代码演示

+ `Cat`:

  ```java
  package reflection;
  
  public class Cat {
      private String name;
      private int age;
  
      public Cat() {
          System.out.println("no-param constructor is executed");
      }
  
      public Cat(String name, int age) {
          this.name = name;
          this.age = age;
          System.out.println("full-params constructor is executed");
      }
  
      private void run() {
          System.out.println("cats run fast");
      }
  
      public void eat() {
          System.out.println("cats like to eat fish");
      }
  
      private String eat(String foodName) {
          return "cats like to eat " + foodName + " best";
      }
  
      @Override
      public String toString() {
          return "Cat{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  
      // 省略getter&setter
  }
  ```

+ `Test4`:

  ```java
  package reflection;
  
  import java.lang.reflect.Method;
  import java.util.Arrays;
  
  public class Test4 {
      public static void main(String[] args) throws Exception {
          Class c = Cat.class;
          Arrays.stream(c.getDeclaredMethods()).forEach(method -> {
              System.out.println(method.getName() + "{"
                      + method.getParameterCount() + " params ; "
                      + "return type: " + method.getReturnType() + "}");
          });
          System.out.println();
  
          Method eat = c.getDeclaredMethod("eat", String.class);
          System.out.println(eat.getName() + "{"
                  + eat.getParameterCount() + " params ; "
                  + "return type: " + eat.getReturnType() + "}");
      }
  }
  ```

+ 控制台输出：

  <img src="images/image-20251118233527094.png" alt="image-20251118233527094" style="zoom:80%;" />



---



#### 4.3 获取成员方法的作用：执行方法

```java
package reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) throws Exception {
        Class c = Cat.class;

        Method eat = c.getDeclaredMethod("eat", String.class);
        Cat cat = new Cat();
        System.out.println(eat.getName() + "{"
                + eat.getParameterCount() + " params ; "
                + "return type: " + eat.getReturnType() + "}");
        eat.setAccessible(true);
        System.out.println(eat.invoke(cat, "猫条"));
    }
}
```

控制台输出：

<img src="images/image-20251118234158128.png" alt="image-20251118234158128" style="zoom:80%;" />



---



## 三、作用及应用场景

### 1.概述

+ **基本作用**：获取一个类的所有成员并进行相关操作。
+ 可以破坏类的封装性。
+ **重要作用**：适合做Java的框架，当代主流框架都会基于反射设计出一些通用功能。



### 2.案例

#### 2.1 背景

假设现在有两个类：

+ `Student`:

  ```java
  public class Student {
      private String name;
      private int age;
      private char sex;
      private double height;
      private String hobby;
  }
  ```

+ `Teacher`:

  ```java
  public class Teacher {
      private String name;
      private double salary;
  }
  ```

然后在主程序中创建出两个对象：

```java
new Student("zsh",20,'m',175.0,"soccer");
new Teacher("aj",3000);
```

我们想要把这两个对象的字段名和对应的值，保存到文件中，那么就需要分别为这两个类编写相应的业务逻辑代码。

可是一旦有成百上千个类，创建出成百上千个实例对象，要实现以上需求，如果还是分别为这成百上千个类编写相应的业务逻辑代码，显然重复累赘、效率低下，是不现实的。



---



#### 2.2 需求

使用反射做一个简易框架，对于任意对象，该框架都可以把该对象的字段名和对应的值，保存到文件中。



#### 2.3 实现思路

1. 定义一个方法，可以接收任意对象；
2. 每收到一个对象，就使用反射获取该对象的`Class`对象，然后获取全部成员变量；
3. 遍历全部成员变量，提取成员变量在该对象中的具体值；
4. 将成员变量名称和对应值写入文件中。



#### 2.4 实现代码

```java
package framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class EasyFrameWork {
    public static void saveObject(Object obj) throws IOException {
        Class c = obj.getClass();

        try (
                PrintStream ps = new PrintStream(new FileOutputStream("test11-reflection/src/fields.txt", true));
        ) {
            ps.println("------------------------" + c.getSimpleName() + "------------------------");
            Arrays.stream(c.getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                try {
                    String name = field.getName();
                    String value = field.get(obj).toString();
                    ps.println(name + "=" + value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            ps.println();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
```



#### 2.5 测试

```java
package framework;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Student student = new Student("zsh", 20, 'm', 175.0, "soccer");
        Teacher teacher = new Teacher("aj", 3000);

        EasyFrameWork.saveObject(student);
        EasyFrameWork.saveObject(teacher);
    }
}
```

运行上述程序后，可以看到src目录下多出了一个`fields.txt`，我们点进去看一下：

<img src="images/image-20251119002920559.png" alt="image-20251119002920559" style="zoom:67%;" />

说明我们制作的简易框架是成功的。









