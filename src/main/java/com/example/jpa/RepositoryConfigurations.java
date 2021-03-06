package com.example.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.jpa.entity")
//@ConditionalOnProperty("spring.datasource.driverClassName")
@EnableJpaRepositories(basePackages = "com.example.jpa.repository")
public class RepositoryConfigurations {
}
