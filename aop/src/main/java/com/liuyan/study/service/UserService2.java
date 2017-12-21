package com.liuyan.study.service;

import com.liuyan.study.annotation.ReadOnly;
import com.liuyan.study.entity.User;
import com.liuyan.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuyan on 2017/11/30.
 */
public class UserService2 implements IUserService {

    @Autowired
    UserRepository userRepository;

    @ReadOnly(readOnly = true)
    public String getUser(String userName) {
        System.out.println(userRepository.findAll());
        return "user2";
    }

    @Override
    @ReadOnly(readOnly = false)
    public User save(User user) {
        System.out.println(userRepository.findAll());
        return userRepository.save(user);
    }
}
