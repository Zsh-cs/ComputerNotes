package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration// 代表这是个Spring配置类
@ComponentScan("com.zsh")// 指定扫描路径，此注解只能添加一次，多个数据需用数组形式，如@ComponentScan({"com.zsh.com.zsh.dao", "com.zsh.service"})
@PropertySource("classpath:jdbc.properties")// 不支持通配符*
public class SpringConfig {
}
