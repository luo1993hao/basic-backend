package com.basicbackend.infrastructure.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.broadcastingmanage.infrastructure.persistence.mybatis")
public class MybatisPlusConfiguration {
}
