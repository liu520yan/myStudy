package com.liuyan.study.base.equals.service.impl;

import com.liuyan.study.base.equals.domain.City;
import com.liuyan.study.base.equals.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyan on 2017/9/30.
 */
@Service
public class CityServiceImpl implements CityService {
    // 模拟数据库，存储 City 信息
    private static Map<Long, City> CITY_DB = new HashMap<>();

    @PostConstruct
    private void init() {
        City city = new City();
        city.setId(1L);
        city.setCityName("广州");
        city.setDescription("描述");
        city.setProvinceId(23L);
        CITY_DB.put(city.getId(), city);
        City city1 = new City();
        city1.setId(2L);
        city1.setCityName("杭州");
        city1.setDescription("描述2");
        city1.setProvinceId(23L);

        CITY_DB.put(city1.getId(), city1);
    }

    @Override
    public List<City> findAllCity() {
        return new ArrayList<>(CITY_DB.values());
    }

    @Override
    public City findCityById(Long id) {
        return CITY_DB.get(id);
    }

    @Override
    public Long saveCity(City city) {
        city.setId(CITY_DB.size() + 1L);
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Long updateCity(City city) {
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Long deleteCity(Long id) {
        CITY_DB.remove(id);
        return id;
    }

}
