package com.liuyan.study.domain;

import lombok.Data;

/**
 * Created by liuyan on 2017/12/22.
 */
@Data
public class Person {
    private Long idp;
    private String lastName;
    private String firstName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Person() {
    }
}
