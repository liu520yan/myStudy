package com.liuyan.study.feign.customer.controller;

import com.liuyan.study.feign.customer.feignClient.DcClient;
import feign.QueryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by liuyan on 2017/12/7.
 */
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/{username}")
    public String dc(@PathVariable("username") String name) {
        return dcClient.consumer(name);
    }

}