package com.study.poi.service;

import com.study.poi.mine.Jiaoche;
import com.study.poi.mine.Jiqi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyan on 2017/10/17.
 */
@Service
public class GetMine {
    @Autowired
    @Qualifier("mineJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    public List<Jiqi> getJiqi() {
        String sql = "SELECT * FROM jiqi ";
        RowMapper<Jiqi> rm = BeanPropertyRowMapper.newInstance(Jiqi.class);
        return jdbcTemplate.query(sql, rm);
    }

    public List<Jiaoche> getJiaoche() {
        String sql = "SELECT * FROM jiaoche ";
        RowMapper<Jiaoche> rm = BeanPropertyRowMapper.newInstance(Jiaoche.class);
        return jdbcTemplate.query(sql, rm);
    }
}
