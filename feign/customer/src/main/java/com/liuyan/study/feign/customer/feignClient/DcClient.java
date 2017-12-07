package com.liuyan.study.feign.customer.feignClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuyan on 2017/12/7.
 */
@FeignClient("eureka-feign-provider")
public interface DcClient {

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    String consumer(@PathVariable("name") String str);

}