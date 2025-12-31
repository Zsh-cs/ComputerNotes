# MyBatisPlus(MP)

> 鸣谢：黑马程序员。（视频链接：【黑马程序员SSM框架教程_Spring+SpringMVC+Maven高级+SpringBoot+MyBatisPlus企业实用开发技术】https://www.bilibili.com/video/BV1Fi4y1S7ix?vd_source=b7f14ba5e783353d06a99352d23ebca9）
>
> ![image-20251229235422308](images/image-20251229235422308.png)



[TOC]



## 一、入门

### 1.入门案例

1. 创建新模块，选择SpringBoot，并配置模块相关基础信息。

2. 选择当前模块需要使用的技术栈，仅勾选`MySQL Driver`。

3. 手动添加MP和Druid的起步依赖：

   ```xml
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
       <!-- 使用与SpringBoot3.5.9版本兼容的MP版本 -->
       <version>3.5.11</version> <!-- 示例版本 -->
   </dependency>
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-jsqlparser</artifactId>
       <version>3.5.11</version> <!-- 必须与上面的版本保持一致 -->
   </dependency>
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid</artifactId>
       <version>1.2.23</version> <!-- 确保支持Spring3.5.9 -->
   </dependency>
   ```

4. 在`application.yml`文件中设置数据源：

   ```yml
   spring:
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/mybatis_plus_db
       username: root
       password: 123456
   ```

5. 根据数据库中要操作的表结构制作实体类`User`：类名与表名对应，属性名与字段名对应。

6. 定义dao接口，继承`BaseMapper<User>`：

   ```java
   @Mapper
   public interface UserDao extends BaseMapper<User> {
   }
   ```

7. 在测试类中注入dao接口，测试功能：

   ```java
   @SpringBootTest
   class ApplicationTests {
   
       @Autowired
       private UserDao userDao;
   
       @Test
       void testGetAll() {
           List<User> users = userDao.selectList(null);
           System.out.println(users);
       }
   }
   ```



### 2.概述

+ MyBatisPlus（简称MP）是基于MyBatis框架开发的增强型工具，旨在简化开发、提高效率。
+ 官网：https://baomidou.com或https://mybatis.plus

+ **特性**：
  + **无侵入**：只做增强不做修改，不会对原有工程产生影响。
  + **损耗小**：启动即会自动注入基本CRUD，性能基本无损耗，直接面向对象操作。
  + **强大的CRUD操作**：内置通用Mapper和通用Service，只需少量手动配置即可实现单表大部分CRUD操作。
  + **支持Lambda**：编写查询条件时，无需担心字段写错。
  + 支持主键自动生成。
  + 内置分页插件。
  + ……



---

---



## 二、标准数据层开发





---

---



## 三、DQL控制





+++

+++



## 四、DML控制





+++

+++



## 五、快速开发





