package com.liuyan.study.config;

import com.liuyan.study.service.IUserService;
import com.liuyan.study.service.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认生成类
 * Created by liuyan on 2017/12/13.
 */
@Slf4j
@Configuration
public class Config {
    @Bean
    @ConditionalOnMissingBean
    public IUserService getCityService() {
        log.info("{}没有实现，生成默认的bean", this.getClass().getName());
        return new UserService2();
    }
}
