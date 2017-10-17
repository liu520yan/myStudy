package com.study.poi.service;

import com.study.poi.commomdata.Province;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncPro;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.reflect.misc.ConstructorUtil;

import java.util.List;

/**
 * Created by liuyan on 2017/10/17.
 */
@Slf4j
@Service
public class InsertTData {

    @Autowired
    @Qualifier("syncJdbcTemplate")
    JdbcTemplate jdbcTemplate;


    public void insertCity(List<SyncCity> syncCities) {
        if (CollectionUtils.isEmpty(syncCities)) {
            log.info("syncCities 不合法！ ");
            return;
        }
        String sql = "insert into SYNC_CITY_copy1(cityName, provinceID,outCityCode,cityId) values ";
        StringBuilder str = new StringBuilder();
        for (SyncCity syncCity : syncCities) {
            str.append("('");
            str.append(syncCity.getCityName());
            str.append("',");
            str.append(syncCity.getProvinceID());
            str.append(",");
            str.append(syncCity.getOutCityCode());
            str.append(",");
            str.append(syncCity.getCityId());
            str.append(")");
            str.append(",");
        }
        sql = sql + str;
        sql = sql.substring(0, sql.length() - 1);
        int num = jdbcTemplate.update(sql);
        log.info("City插入数量 ： " + num);
    }

    public void insertPro(List<SyncPro> syncPros) {
        if (CollectionUtils.isEmpty(syncPros)) {
            log.info("syncCities 不合法！ ");
            return;
        }
        String sql = "insert into SYNC_PROVINCE_copy1 (provinceId,provinceName,outProCode) values";
        StringBuilder str = new StringBuilder();
        for (SyncPro syncPro : syncPros) {
            str.append("(");
            str.append(syncPro.getProvinceId());
            str.append(",'");
            str.append(syncPro.getProvinceName());
            str.append("','");
            str.append(syncPro.getOutProCode());
            str.append("'),");
        }
        sql = sql + str;
        sql = sql.substring(0, sql.length() - 1);
        int num = jdbcTemplate.update(sql);
        log.info("Pro插入数量 ： " + num);
    }
}
