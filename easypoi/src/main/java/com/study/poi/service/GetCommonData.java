package com.study.poi.service;

import com.study.poi.commomdata.City;
import com.study.poi.commomdata.District;
import com.study.poi.commomdata.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author liuyan
 * @date 2017/10/17
 */
@Service
public class GetCommonData {
    @Autowired
    @Qualifier("commomdataJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    public List<City> getCity() {
        String sql = "SELECT a.id ,a.cityName,a.provinceID  FROM BASE_CITY a ";
        RowMapper<City> rm = BeanPropertyRowMapper.newInstance(City.class);
        return jdbcTemplate1.query(sql, rm);
    }

    public List<Province> getProvince() {
        String sql = "SELECT b.id ,b.provinceName from BASE_PROVINCE b;";
        RowMapper<Province> rm = BeanPropertyRowMapper.newInstance(Province.class);
        return jdbcTemplate1.query(sql, rm);
    }

    public List<District> getDistrict() {
        String sql = "SELECT c.id ,c.districtName FROM BASE_DISTRICT c;";
        RowMapper<District> rm = BeanPropertyRowMapper.newInstance(District.class);
        return jdbcTemplate1.query(sql, rm);
    }
}
