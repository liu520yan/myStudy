package com.study.poi.service;

import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncDistrict;
import com.study.poi.sync.SyncPro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
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


    /**
     * 插入city表
     *
     * @param syncCities
     */
    public void insertCity(Collection<SyncCity> syncCities) {
        if (CollectionUtils.isEmpty(syncCities)) {
            log.info("syncCities 不合法！ ");
            return;
        }
        String sql = "insert into SYNC_CITY_copy(cityName, provinceID,outCityCode,cityId) values ";
        StringBuilder str = new StringBuilder();
        for (SyncCity syncCity : syncCities) {
            str.append("('");
            str.append(syncCity.getCityName());
            str.append("',");
            str.append(syncCity.getProvinceID());
            str.append(",'");
            str.append(syncCity.getOutCityCode());
            str.append("',");
            str.append(syncCity.getCityId());
            str.append(")");
            str.append(",");
        }
        sql = sql + str;
        sql = sql.substring(0, sql.length() - 1);
        int num = jdbcTemplate.update(sql);
        log.info("City插入数量 ： " + num);
    }

    /**
     * 插入省表
     *
     * @param syncPros
     */
    public void insertPro(List<SyncPro> syncPros) {
        if (CollectionUtils.isEmpty(syncPros)) {
            log.info("syncCities 不合法！ ");
            return;
        }
        String sql = "insert into SYNC_PROVINCE_copy(provinceId,provinceName,outProCode) values";
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
    }

    /**
     * 插入区
     *
     * @param syncDistricts
     */
    public void insertDis(Collection<SyncDistrict> syncDistricts) {
        if (CollectionUtils.isEmpty(syncDistricts)) {
            log.info("syncDistricts 不合法");
            return;
        }

        String sql = "insert into SYNC_DISTRICT_copy(cityID,districtId,districtName,outDisCode) value ";
        StringBuilder str = new StringBuilder();
        for (SyncDistrict syncDistrict : syncDistricts) {
            str.append("(");
            str.append(syncDistrict.getCityID());
            str.append(",");
            str.append(syncDistrict.getDistrictId());
            str.append(",");
            str.append("'" + syncDistrict.getDistrictName() + "'");
            str.append(",");
            str.append(syncDistrict.getOutDisCode());
            str.append("),");
        }

        sql = sql + str;
        sql = sql.substring(0, sql.length() - 1);
        int num = jdbcTemplate.update(sql);
//        String sql = "insert into SYNC_DISTRICT_copy1 (cityID,districtId,districtName,outDisCode) value(?,?,?,?)";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                SyncDistrict syncDistrict = syncDistricts.get(i);
//                ps.setString(1, syncDistrict.getCityID());
//                ps.setString(2, syncDistrict.getDistrictId());
//                ps.setString(3, syncDistrict.getDistrictName());
//                ps.setString(4, syncDistrict.getOutDisCode());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return syncDistricts.size();
//            }
//        });

    }
}
