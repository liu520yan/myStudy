package com.liuyan.study.config;

import com.liuyan.study.service.DynamicDataSource;
import com.liuyan.study.service.IUserService;
import com.liuyan.study.service.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.liuyan.study.service.DataSourceContextHolder.DATA_SOURCE_READ;
import static com.liuyan.study.service.DataSourceContextHolder.DATA_SOURCE_WRITE;


/**
 * 默认生成类
 * Created by liuyan on 2017/12/13.
 */
@Slf4j
@Configuration
public class Config {

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    @ConditionalOnMissingBean
    public IUserService getCityService() {
        log.info("{}没有实现，生成默认的bean", this.getClass().getName());
        return new UserService2();
    }


    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DATA_SOURCE_WRITE, primaryDataSource());
        targetDataSources.put(DATA_SOURCE_READ, secondaryDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        return dynamicDataSource;
    }

    @Bean
    public JpaTransactionManager getJpaTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        LocalContainerEntityManagerFactoryBean factoryBean = entityManagerFactory(builder);
        jpaTransactionManager.setEntityManagerFactory(factoryBean.getObject());
        return jpaTransactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Assert.notNull(builder, "builder could not null !!");
        return builder
                .dataSource(dynamicDataSource())
                .packages("com.liuyan.study.entity")
                .persistenceUnit("primaryPersistenceUnit")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager initJpaTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
        return jpaTransactionManager;
    }

    @Bean
    public JdbcTemplate secondaryJdbcTemplate() {
        return new JdbcTemplate(dynamicDataSource());
    }
}
