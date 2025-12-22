package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.zsh.service"})
@PropertySource("jdbc.properties")
@Import({MyBatisConfig.class, JdbcConfig.class})
public class SpringConfig {
}
