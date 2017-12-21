package com.liuyan.study.entity;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import java.lang.annotation.Inherited;

/**
 * Created by liuyan on 2017/12/21.
 */
@Data
@Entity
public class User {
    @Id
    long id;
    String name;
}
