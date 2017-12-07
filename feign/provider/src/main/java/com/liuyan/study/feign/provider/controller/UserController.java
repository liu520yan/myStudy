package com.liuyan.study.feign.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/12/7.
 */
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @GetMapping("/user/{name}")
    public String name(@PathVariable("name") String name) {
        logger.info("param is {}", name);
        return name;
    }
}
