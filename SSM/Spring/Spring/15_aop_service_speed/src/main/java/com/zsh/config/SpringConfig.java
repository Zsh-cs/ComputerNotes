package com.zsh.config;

import com.zsh.config.JdbcConfig;
import com.zsh.config.MybatisConfig;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.zsh")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig {
}
