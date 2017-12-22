package com.liuyan.study.mapper;

import com.liuyan.study.domain.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liuyan on 2017/12/22.
 */
public class PersonMapper implements RowMapper {

    private static final String ID_COLUMN = "idp";
    private static final String NICKNAME_COLUMN = "first_name";
    private static final String EMAIL_COLUMN = "last_name";


    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setIdp(resultSet.getLong(ID_COLUMN));
        person.setFirstName(resultSet.getString(NICKNAME_COLUMN));
        person.setLastName(resultSet.getString(EMAIL_COLUMN));
        return person;
    }
}