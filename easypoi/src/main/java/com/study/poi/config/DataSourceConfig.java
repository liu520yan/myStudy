package com.study.poi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @author liuyan
 * @date 2017/10/17
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean(name = "commomdataDataSource")
    @Qualifier("commomdataDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.commomdata")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "commomdataJdbcTemplate")
    public JdbcTemplate commomdataJdbcTemplate(
            @Qualifier("commomdataDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "syncDataSource")
    @Qualifier("syncDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.sync")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "syncJdbcTemplate")
    public JdbcTemplate syncJdbcTemplate(
            @Qualifier("syncDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mineDataSource")
    @Qualifier("mineDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mine")
    public DataSource thirdDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mineJdbcTemplate")
    public JdbcTemplate mineJdbcTemplate(
            @Qualifier("mineDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
