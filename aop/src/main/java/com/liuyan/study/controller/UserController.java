package com.liuyan.study.controller;

import com.liuyan.study.annotation.User;
import com.liuyan.study.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/11/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @User
    @RequestMapping(method = RequestMethod.GET)
    public String getUser() {
        return userService.getUser();
    }
}
