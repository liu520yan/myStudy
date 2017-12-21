package com.liuyan.study.repository;

import com.liuyan.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liuyan on 2017/12/21.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
