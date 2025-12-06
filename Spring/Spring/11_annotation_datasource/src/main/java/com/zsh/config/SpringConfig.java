package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration// 代表这是个Spring配置类
@Import(JdbcConfig.class)// 导入配置
@ComponentScan("com.zsh.dao")
public class SpringConfig {
}
