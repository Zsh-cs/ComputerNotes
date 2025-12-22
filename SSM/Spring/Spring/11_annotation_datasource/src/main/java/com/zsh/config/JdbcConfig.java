package com.zsh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zsh.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

public class JdbcConfig {

    @Value("com.mysql.cj.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;
    @Value("root")
    private String username;
    @Value("123456")
    private String password;

    // 1.定义一个方法来获得要管理的第三方bean
    // 2.添加@Bean表示该方法的返回值是一个bean
    @Bean
    public DataSource getDataSource(BookDao bookDao){// 容器会自动装配bookDao(byType)
        System.out.println(bookDao);
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
