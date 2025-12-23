# SSM整合

> 鸣谢：黑马程序员
>
> ![image-20251127003326182-1765733558407-2](images/image-20251127003326182-1765733558407-2.png)



**项目结构**：

```bash
src
├─main
│  ├─java
│  │  └─com
│  │      └─zsh
│  │          ├─config
│  │          │      JdbcConfig.java
│  │          │      MyBatisConfig.java
│  │          │      ServletConfig.java
│  │          │      SpringConfig.java
│  │          │      SpringMvcConfig.java
│  │          │
│  │          ├─controller
│  │          │      BookController.java
│  │          │      Code.java
│  │          │      Result.java
│  │          │
│  │          ├─dao
│  │          │      BookDao.java
│  │          │
│  │          ├─domain
│  │          │      Book.java
│  │          │
│  │          └─service
│  │              │  BookService.java
│  │              │
│  │              └─impl
│  │                      BookServiceImpl.java
│  │
│  ├─resources
│  │      jdbc.properties
│  │
│  └─webapp
│      │  index.jsp
│      │
│      └─WEB-INF
│              web.xml
│
└─test
    └─java
        └─com
            └─zsh
                └─service
                        BookServiceTest.java
```

<img src="images/image-20251223003157584.png" alt="image-20251223003157584" style="zoom:80%;" />

<img src="images/image-20251223003213599.png" alt="image-20251223003213599" style="zoom: 80%;" />



---



## PartⅠ 后端开发

### 一、SSM整合流程

1. 创建工程
2. SSM整合配置：
   + Spring：`SpringConfig`
   + SpringMVC：
     + `ServletConfig`
     + `SpringMvcConfig`
   + MyBatis：
     + `MyBatisConfig`
     + `JdbcConfig`
     + `jdbc.properties`

3. 功能模块开发：
   + 表与实体类
   + dao（接口+自动代理）
   + service（接口+实现类）：业务层接口测试（整合`JUnit`）
   + controller：表现层接口测试（`Postman`）



---



### 二、`config`包—核心配置类

<img src="images/image-20251222233725734.png" alt="image-20251222233725734" style="zoom:80%;" />

#### 1.`jdbc.properties`+`JdbcConfig`

+ `jdbc.properties`:

  ```properties
  jdbc.driver=com.mysql.cj.jdbc.Driver
  jdbc.url=jdbc:mysql://localhost:3306/ssm_db
  jdbc.username=root
  jdbc.password=123456
  ```

+ `JdbcConfig`:

  ```java
  package com.zsh.config;
  
  import com.alibaba.druid.pool.DruidDataSource;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.context.annotation.Bean;
  
  import javax.sql.DataSource;
  
  public class JdbcConfig {
  
      @Value("${jdbc.driver}")
      private String driver;
      @Value("${jdbc.url}")
      private String url;
      @Value("${jdbc.username}")
      private String username;
      @Value("${jdbc.password}")
      private String password;
  
      @Bean
      public DataSource dataSource(){
          DruidDataSource dataSource=new DruidDataSource();
          dataSource.setDriverClassName(driver);
          dataSource.setUrl(url);
          dataSource.setUsername(username);
          dataSource.setPassword(password);
          return dataSource;
      }
      
      @Bean
      public PlatformTransactionManager transactionManager(DataSource dataSource){
          DataSourceTransactionManager manager=new DataSourceTransactionManager();
          manager.setDataSource(dataSource);
          return manager;
      }
  }
  ```



#### 2.`MyBatisConfig`

```java
package com.zsh.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.zsh.domain");
        return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setBasePackage("com.zsh.dao");
        return msc;
    }
}
```



#### 3.`SpringConfig`

```java
package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.zsh.service"})
@PropertySource("classpath:jdbc.properties")
@Import({MyBatisConfig.class, JdbcConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}
```



#### 4.`SpringMvcConfig`

```java
package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.zsh.controller")
@EnableWebMvc
public class SpringMvcConfig {
}
```



#### 5.`ServletConfig`

```java
package com.zsh.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // POST请求中文乱码处理：设置过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter=new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
```



---



### 三、功能模块开发

#### 1.`Book`

```java
package com.zsh.domain;

public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;

    public Book(){}

    public Book(Integer id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    
    // 省略getter&setter

}
```



#### 2.`BookDao`

```java
package com.zsh.dao;

import com.zsh.domain.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    @Insert("insert into books (type, name, description) value (#{type},#{name},#{description})")
    int save(Book book);

    @Delete("delete from books where id=#{id}")
    int delete(Integer id);

    @Update("update books set type=#{type},name=#{name},description=#{description} where id=#{id}")
    int update(Book book);

    @Select("select * from books where id=#{id}")
    Book getById(Integer id);

    @Select("select * from books")
    List<Book> getAll();
}

```



#### 3.`BookService`+`BookServiceImpl`+`BookServiceTest`

+ `BookService`:

  ```java
  package com.zsh.service;
  
  import com.zsh.domain.Book;
  import org.springframework.transaction.annotation.Transactional;
  
  import java.util.List;
  
  @Transactional
  public interface BookService {
      boolean save(Book book);
      boolean delete(Integer id);
      boolean update(Book book);
      Book getById(Integer id);
      List<Book> getAll();
  }
  ```

+ `BookServiceImpl`:

  ```java
  package com.zsh.service.impl;
  
  import com.zsh.controller.Code;
  import com.zsh.dao.BookDao;
  import com.zsh.domain.Book;
  import com.zsh.exception.BuisnessException;
  import com.zsh.exception.SystemException;
  import com.zsh.service.BookService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  
  import java.util.List;
  
  @Service
  public class BookServiceImpl implements BookService {
  
      @Autowired
      private BookDao bookDao;
  
      @Override
      public boolean save(Book book) {
          return bookDao.save(book) > 0 ? true : false;
      }
  
      @Override
      public boolean delete(Integer id) {
          return bookDao.delete(id) > 0 ? true : false;
      }
  
      @Override
      public boolean update(Book book) {
          return bookDao.update(book) > 0 ? true : false;
      }
  
      @Override
      public Book getById(Integer id) {
          return bookDao.getById(id);
      }
  
      @Override
      public List<Book> getAll() {
          return bookDao.getAll();
      }
  }
  ```

+ `BookServiceTest`:

  ```java
  package com.zsh.service;
  
  import com.zsh.config.SpringConfig;
  import com.zsh.domain.Book;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.test.context.ContextConfiguration;
  import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
  
  import java.util.List;
  
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes = SpringConfig.class)
  public class BookServiceTest {
  
      @Autowired
      private BookService bookService;
  
      @Test
      public void testGetById(){
          Book book = bookService.getById(1);
          System.out.println(book);
      }
  
      @Test
      public void testGetAll(){
          List<Book> books=bookService.getAll();
          System.out.println(books);
      }
  }
  ```



#### 4.`BookController`

```java
package com.zsh.controller;

import com.zsh.domain.Book;
import com.zsh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public boolean save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }

    @PutMapping
    public boolean update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

}
```



---

---



## PartⅡ 表现层与前端数据交互

### 一、设置统一的数据返回结果类`Result`

> [!Tip]
>
> `Result`类中的字段并不是固定的，可以根据需要自行增减，同时要提供若干个构造方法，方便操作。

```java
package com.zsh.controller;

public class Result {
    private Integer code;// 状态码
    private Object data;// 数据
    private String msg;// 响应消息

    public Result() {

    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    // 省略getter&setter
}
```



### 二、设置统一的数据返回结果状态码类`Code`

```java
package com.zsh.controller;

public class Code {
    // 结尾1代表成功，0代表失败

    public static final Integer SAVE_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    public static final Integer SAVE_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;
}
```



### 三、重构`BookController`

```java
package com.zsh.controller;

import com.zsh.domain.Book;
import com.zsh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "数据查询成功" : "数据查询失败，请重试";
        return new Result(code, book, msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> books = bookService.getAll();
        Integer code = books != null ? Code.GET_OK : Code.GET_ERR;
        String msg = books != null ? "数据查询成功" : "数据查询失败，请重试";
        return new Result(code, books, msg);
    }

}
```



---

---



## PartⅢ 项目异常处理

### 一、异常处理器

#### 1.异常的常见位置和诱因

|   常见位置   |                           诱因                           |
| :----------: | :------------------------------------------------------: |
| 框架内部异常 |                        使用不合规                        |
|  数据层异常  |             外部服务器故障，如服务器访问超时             |
|  业务层异常  |   业务逻辑书写错误，如遍历业务书写操作导致索引越界异常   |
|  表现层异常  |  数据收集、校验等规则错误，如不匹配的数据类型间导致异常  |
|  工具类异常  | 工具类书写不严谨不够健壮，如长期未释放某个必须释放的连接 |



#### 2.异常处理思路

各个层级均有可能出现异常，我们将所有异常都抛出到表现层进行统一处理。

表现层处理异常，若每个方法中单独书写，代码臃肿且意义不大，如何解决？——**AOP**思想。



#### 3.异常处理器

```java
package com.zsh.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 项目异常统一处理
 */
@RestControllerAdvice// REST风格
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)// 拦截所有异常并处理
    public Result handleException(Exception e) {
        System.out.println("catch an exception...");
        return new Result(666, null, "catch an exception");
    }
}
```



---



### 二、项目异常处理方案

#### 1.项目异常分类

+ **业务异常(BusinessException)**：
  + 规范的用户行为操作产生的异常，如用户在年龄输入框输入了“十八”而非“18”。。
  + 不规范的用户行为操作产生的异常，如用户在地址栏输入了`http://localhost/users/heihei`而非`http://localhost/books/1`。
+ **系统异常(SystemException)**：项目运行过程中可预计且无法避免的异常，如服务器宕机。
+ **其他异常(Exception)**：编程人员未预料到的异常，如找不到指定路径的文件。



#### 2.处理方案

+ **业务异常**：发送**对应**消息给用户，提醒用户进行规范操作。
+ **系统异常**：
  + 发送**固定**消息给用户，安抚用户。
  + 发送**特定**消息给运维人员，提醒他们维护。
  + 记录日志。
+ **其他异常**：
  + 发送**固定**消息给用户，安抚用户。
  + 发送**特定**消息给开发人员，提醒他们将此异常纳入预期范围内。
  + 记录日志。



#### 3.代码实现

##### 3.1 定义`BusinessException`和`SystemException`

+ `BusinessException`:

  ```java
  package com.zsh.exception;
  
  public class BuisnessException extends RuntimeException {
      private Integer code;
  
      public BuisnessException(Integer code, String message) {
          super(message);
          this.code = code;
      }
  
      public BuisnessException(Integer code, String message, Throwable cause) {
          super(message, cause);
          this.code = code;
      }
  
      public Integer getCode() {
          return code;
      }
  
      public void setCode(Integer code) {
          this.code = code;
      }
  }
  ```

+ `SystemException`:

  ```java
  package com.zsh.exception;
  
  public class SystemException extends RuntimeException {
      private Integer code;
  
      public SystemException(Integer code, String message) {
          super(message);
          this.code = code;
      }
  
      public SystemException(Integer code, String message, Throwable cause) {
          super(message, cause);
          this.code = code;
      }
  
      public Integer getCode() {
          return code;
      }
  
      public void setCode(Integer code) {
          this.code = code;
      }
  }
  ```



##### 3.2 更新`Code`类

```java
package com.zsh.controller;

public class Code {
    // 结尾1代表成功，0代表失败

    public static final Integer SAVE_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    public static final Integer SAVE_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;

    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;
    public static final Integer BUSINESS_ERR = 60001;
    public static final Integer UNKNOWN_ERR = 99999;
}
```



##### 3.3 在`BookServiceImpl`中触发自定义异常

```java
package com.zsh.service.impl;

import com.zsh.controller.Code;
import com.zsh.dao.BookDao;
import com.zsh.domain.Book;
import com.zsh.exception.BuisnessException;
import com.zsh.exception.SystemException;
import com.zsh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean save(Book book) {
        bookDao.save(book);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    @Override
    public boolean update(Book book) {
        bookDao.update(book);
        return true;
    }

    @Override
    public Book getById(Integer id) {
        if (id < 1) {
            throw new BuisnessException(Code.BUSINESS_ERR, "您输入的id不合法，请重新输入！");
        }

        try {
            int i = 1 / 0;// 模拟异常
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器访问超时，请重试！", e);
        }
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
```



##### 3.4 重构`ProjectExceptionAdvice`

```java
package com.zsh.controller;

import com.zsh.exception.BuisnessException;
import com.zsh.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 项目异常统一处理
 */
@RestControllerAdvice// REST风格
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)// 拦截系统异常并处理
    public Result handleSystemException(SystemException e) {
        // 1.记录日志
        // 2.发送消息给运维人员
        // 3.发送邮件给开发人员
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(BuisnessException.class)// 拦截业务异常并处理
    public Result handleBusinessException(BuisnessException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)// 拦截其他异常并处理
    public Result handleException(Exception e) {
        // 1.记录日志
        // 2.发送消息给运维人员
        // 3.发送邮件给开发人员
        return new Result(Code.UNKNOWN_ERR, null, "系统繁忙，请稍后再试！");
    }
}
```



---

---



## PartⅣ 前后端协议联调

### 一、更新`config`包下的核心配置类

#### 1.新增`SpringMvcSupport`

```java
package com.zsh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Override
    // 放行静态资源
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
```



#### 2.重构`SpringMvcConfig`

```java
package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zsh.controller", "com.zsh.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
```



---



### 二、完善功能模块

#### 

