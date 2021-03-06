package com.liuyan.study.dao;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久层操作接口
 * <p>
 * Created by bysocket on 21/07/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
