package com.study.poi.service;

import com.study.poi.commomdata.City;
import com.study.poi.commomdata.District;
import com.study.poi.commomdata.Province;
import com.study.poi.mine.Jiaoche;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncPro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liuyan on 2017/10/17.
 */
@Slf4j
@Service
public class InsertJiaoChe {

    @Autowired
    InsertTData insertTData;

    @Autowired
    GetCommonData getCommonData;

    @Autowired
    GetMine getMine;

    public void insertPro() {
        List<SyncPro> provinces1 = new ArrayList<>();
        List<Province> provinces = getCommonData.getProvince();
        List<Jiaoche> jiaoches = getMine.getJiaoche();

        jiaoches.stream()
                .forEach(s -> s.setCityname(null));
        jiaoches.stream()
                .forEach(s -> s.setCitycode(null));

        jiaoches = jiaoches.stream().distinct().collect(Collectors.toList());

        List<Jiaoche> jiaoches1 = new ArrayList<>();
        for (Province province : provinces) {
            String proName = province.getProvinceName();
            for (Jiaoche jiaoche : jiaoches) {
                if (proName.equals(jiaoche.getProname())) {
                    SyncPro syncPro = new SyncPro();
                    syncPro.setOutProCode(jiaoche.getProcode());
                    syncPro.setProvinceId(province.getId());
                    syncPro.setProvinceName(proName);
                    provinces1.add(syncPro);
                    jiaoches1.add(jiaoche);
                }
            }

        }
        if (jiaoches.size() - jiaoches1.size() > 0) {
            jiaoches.removeAll(jiaoches1);
            log.info("轿车没有匹配的数量 ：" + jiaoches.size());
            log.info("没有匹配的数据： ");
            jiaoches.stream().forEach(System.out::println);
        } else {
            log.info("省完全匹配");
        }
        //插入数据
        insertTData.insertPro(provinces1);
    }

    public void insetCity() {
        List<SyncCity> syncCities = new ArrayList<>();
        List<City> cities = getCommonData.getCity();
        List<Jiaoche> jiaoches = getMine.getJiaoche();
        List<Jiaoche> jiaoches2 = new ArrayList<Jiaoche>();
        for (City city : cities) { // 内部
            String cityName = city.getCityName();
            for (Jiaoche jiaoche : jiaoches) { //外部
                if (jiaoche.getCityname().equals(cityName)) {
                    SyncCity syncCity = new SyncCity();
                    syncCity.setCityId(city.getId());
                    syncCity.setCityName(cityName);
                    syncCity.setOutCityCode(jiaoche.getCitycode());
                    syncCity.setProvinceID(city.getProvinceID());
                    syncCities.add(syncCity);
                    jiaoches2.add(jiaoche);
                }
            }
        }
        if (jiaoches.size() - jiaoches2.size() > 0) {
            jiaoches.removeAll(jiaoches2);
            log.info("city 没有匹配的数量： " + jiaoches.size());
            log.info("city 没有匹配的数据： ");
            jiaoches.stream().forEach(System.out::println);
        } else {
            log.info("city 全部匹配");
        }
        //插入数据
        insertTData.insertCity(syncCities);
    }
}
