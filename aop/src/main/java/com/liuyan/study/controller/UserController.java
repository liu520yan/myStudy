package com.liuyan.study.controller;

import com.liuyan.study.annotation.User;
import com.liuyan.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/11/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService2;

    @Autowired
    ApplicationContext context;

    @User
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(@RequestParam(required = false) String userName) {
        return userService2.getUser(userName);
    }
}
