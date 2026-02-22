package com.codegym.jira.common.internal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource h2DataSource(@Value("${spring.datasource.url}") String url,
                                   @Value("${spring.datasource.driver-class-name}") String driver,
                                   @Value("${spring.datasource.username}") String username,
                                   @Value("${spring.datasource.password}") String password) {
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean(name = "dataSource")
    @Profile("!test")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    }
