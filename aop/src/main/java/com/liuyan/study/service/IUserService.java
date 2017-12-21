package com.liuyan.study.service;

import com.liuyan.study.entity.User;

/**
 * Created by liuyan on 2017/12/13.
 */
public interface IUserService {
    String getUser(String userName);

    User save(User user);
}
