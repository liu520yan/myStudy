package com.study.poi.dao;

import com.study.poi.commomdata.City;
import com.study.poi.commomdata.District;
import com.study.poi.commomdata.Province;
import org.hibernate.annotations.Source;
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
        String sql = "SELECT a.id ,a.cityName,a.provinceID,b.provinceName  FROM BASE_CITY a INNER JOIN BASE_PROVINCE b ON a.provinceID = b.id ";
        RowMapper<City> rm = BeanPropertyRowMapper.newInstance(City.class);
        return jdbcTemplate1.query(sql, rm);
    }

    public List<Province> getProvince() {
        String sql = "SELECT b.id ,b.provinceName from BASE_PROVINCE b;";
        RowMapper<Province> rm = BeanPropertyRowMapper.newInstance(Province.class);
        return jdbcTemplate1.query(sql, rm);
    }

    public List<District> getDistrict() {
        String sql = "SELECT\n" +
                "\ta.id,\n" +
                "\ta.cityName,\n" +
                "\tc.districtName,\n" +
                "\tb.provinceName,\n" +
                "\tc.cityID\n" +
                "FROM\n" +
                "\tBASE_CITY a\n" +
                "INNER JOIN BASE_PROVINCE b ON a.provinceID = b.id\n" +
                "INNER JOIN BASE_DISTRICT c ON c.cityID = a.id;";
        RowMapper<District> rm = BeanPropertyRowMapper.newInstance(District.class);
        return jdbcTemplate1.query(sql, rm);
    }

    public District getFirstDisByCityCode(String cityCode) {
        String sql = "SELECT a.districtName,a.cityID,a.id  FROM BASE_DISTRICT a WHERE a.cityID = " + cityCode + " ORDER BY a.id DESC LIMIT 1 ";
        RowMapper<District> rm = BeanPropertyRowMapper.newInstance(District.class);
        return jdbcTemplate1.queryForObject(sql, rm);
    }

    //某个省下是否有某个市
    public int getCityUnderPro(String proName, String cityName) {
        String sql = "SELECT count(*) FROM BASE_CITY a ,BASE_PROVINCE b WHERE a.provinceID = b.id AND b.provinceName = '" + proName + "' AND a.cityName = '" + cityName + "' ";
        return jdbcTemplate1.queryForObject(sql, Integer.class);
    }

    public String getProIdByProName(String proName) {
        String sql = "SELECT a.id FROM BASE_PROVINCE a WHERE a.provinceName = '" + proName + "'";
        return jdbcTemplate1.queryForObject(sql, String.class);
    }
}
