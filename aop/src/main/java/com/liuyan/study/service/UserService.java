package com.liuyan.study.service;

import org.springframework.stereotype.Service;

/**
 * Created by liuyan on 2017/11/30.
 */
public class UserService implements IUserService {
    public String getUser(String userName) {
        return userName;
    }
}
