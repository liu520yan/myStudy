package com.liuyan.study.service;

import com.liuyan.study.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by liuyan on 2017/11/30.
 */
public class UserService implements IUserService {
    public String getUser(String userName) {
        return userName;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
