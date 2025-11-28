# Spring

> 鸣谢：黑马程序员
>
> ![image-20251127003326182](images/image-20251127003326182.png)

<img src="images/image-20251126232800961.png" alt="image-20251126232800961" style="zoom:67%;" />



## 一、核心容器

### 1.`IoC/DI`

#### 1.1 `IoC`

+ `IoC(Inversion of Control)`：控制反转。
+ **核心思想**：将对象的创建控制权由程序内部转移到**外部**。也就是使用对象时，在程序中不要主动使用`new`产生对象，而是转换为由外部提供对象。

#### 1.2 `IoC`容器

+ Spring提供了一个容器用来充当`IoC`思想中的**外部**，称为`IoC`容器。



---



#### 1.3 `Bean`

> [!IMPORTANT]
>
> `IoC`容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在`IoC`容器中统称为`Bean`。

##### P1 `Bean`的作用范围(`scope`)

+ **默认是单例**。

+ 适合交给容器管理的`Bean`：接口层对象、业务层对象、数据层对象、工具对象；
+ 不适合交给容器管理的`Bean`：封装实体的域对象。

##### P2 实例化`Bean`的方式

+ **常用**：提供无参构造器（`private`也可）。
+ 使用静态工厂。
+ 使用实例工厂。
+ **重点**：使用`FactoryBean`。（方式三的变种）

##### P3 `Bean`的生命周期及控制

+ `Bean`的生命周期：

  + *Step1*：**初始化容器**。
    1. 创建对象（内存分配）
    2. 执行构造器
    3. 执行属性注入（调用`setter`）
    4. 执行`bean`初始化方法

  + *Step2*：**使用`bean`**。

  + *Step3*：**关闭/销毁容器**。

+ `Bean`的生命周期控制：

  + 控制方式一：

    <img src="images/image-20251129000246645.png" alt="image-20251129000246645" style="zoom:67%;" />

  + 控制方式二：接口控制

    <img src="images/image-20251129000325628.png" alt="image-20251129000325628" style="zoom:67%;" />


##### P4 `Bean`的销毁时机

+ 容器关闭前触发`Bean`的销毁。
+ **关闭容器的方式**：
  + 手动关闭：调用`ConfigurableApplicationContext`接口的`close()`方法。
  + 注册关闭钩子，先关闭容器再退出JVM：调用`ConfigurableApplicationContext`接口的`registerShutdownHook()`方法。



---



#### 1.4 `DI`

+ `DI(Dependency Injection)`：依赖注入。

+ **核心思想**：在`IoC`容器中建立`Bean`与`Bean`之间的依赖关系。

  <img src="images/image-20251126234842800.png" alt="image-20251126234842800" style="zoom:67%;" />



---



### 2.容器基本操作



---



## 二、数据访问与集成



---



## 三、`AOP`



---



## 四、事务



---



## 五、框架整合