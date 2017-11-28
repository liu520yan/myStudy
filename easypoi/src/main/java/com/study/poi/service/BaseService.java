package com.study.poi.service;

import com.study.poi.commomdata.City;
import com.study.poi.commomdata.District;
import com.study.poi.commomdata.Province;
import com.study.poi.dao.GetCommonData;
import com.study.poi.mine.Jiqi;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncDistrict;
import com.study.poi.sync.SyncPro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by liuyan on 2017/10/18.
 */
@Slf4j
public class BaseService<T> {

    @Autowired
    GetCommonData getCommonData;

    List<District> districts;

    @Autowired
    InsertTData insertTData;

    private static LinkedBlockingQueue a = new LinkedBlockingQueue(100);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            1,
            1,
            TimeUnit.MINUTES,
            a
            ,
            new ThreadFactory() {
                private AtomicInteger id = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("MyThread-study-" + id.addAndGet(1));
                    return thread;
                }
            }, new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 获取省列表
     *
     * @param t
     * @return
     */
    protected List<SyncPro> getProList(List<T> t) {

        List<Jiqi> jiqis = new ArrayList<>();

        for (T a : t) {
            Jiqi jiqi = new Jiqi();
            BeanUtils.copyProperties(a, jiqi);
            jiqis.add(jiqi);
        }
        List<Province> provinces = getCommonData.getProvince();
        log.info("标准省数量 ：" + provinces.size());
        List<SyncPro> provinces1 = new ArrayList<>();

        //除重
        List<Jiqi> jiqis1 = new ArrayList<>();
        for (Jiqi jiqi : jiqis) {
            if (!jiqi.proEquals(jiqis1)) {
                jiqis1.add(jiqi);
            }
        }
        jiqis = jiqis1;

        log.info("输入省数量 ：" + jiqis.size());

        List<Jiqi> jiaoches1 = new ArrayList<>();
        for (Province province : provinces) {
            String proName = province.getProvinceName();
            for (Jiqi jiaoche : jiqis) {
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
        if (jiqis.size() - jiaoches1.size() > 0) {
            jiqis.removeAll(jiaoches1);
            log.info("轿车没有匹配的数量 ：" + jiqis.size());
            log.info("没有匹配的数据： ");
            jiqis.stream().forEach(System.out::println);
        } else {
            log.info("省完全匹配");
        }
        log.info("输出省数量 ：" + provinces1.size());
        return provinces1;
    }

    /**
     * 获取城市列表
     *
     * @param t
     * @return
     */
    protected List<SyncCity> getCityList(List<T> t) {

        List<Jiqi> jiqis = new ArrayList<>();
        for (T a : t) {
            Jiqi jiqi = new Jiqi();
            BeanUtils.copyProperties(a, jiqi);
            jiqis.add(jiqi);
        }

        //除重
        List<Jiqi> jiqis1 = new ArrayList<>();
        for (Jiqi jiqi : jiqis) {
            if (!jiqi.cityEquals(jiqis1)) {
                jiqis1.add(jiqi);
            }
        }
        jiqis = jiqis1;
        log.info("输入城市数量：" + jiqis.size());

        List<City> cities = getCommonData.getCity();
        log.info("标准城市数量：" + cities.size());
        List<SyncCity> syncCities = new ArrayList<>();
        List<Jiqi> jiaoches2 = new ArrayList<>();
        for (City city : cities) { // 内部
            String cityName = city.getCityName();
            String proName = city.getProvinceName();
            for (Jiqi jiqi : jiqis) { //外部
                if (jiqi.getCityname().equals(cityName)
                        && jiqi.getProname().equals(proName)) {
                    SyncCity syncCity = new SyncCity();
                    syncCity.setCityId(city.getId());
                    syncCity.setCityName(cityName);
                    syncCity.setOutCityCode(jiqi.getCitycode());
                    syncCity.setProvinceID(city.getProvinceID());
                    syncCities.add(syncCity);
                    jiaoches2.add(jiqi);
                }
            }
        }
        if (jiqis.size() - jiaoches2.size() > 0) {
            jiqis.removeAll(jiaoches2);
            log.info("city 没有匹配的数量： " + jiqis.size());
            log.info("city 没有匹配的数据： ");
            jiqis.stream().forEach(System.out::println);
        } else {
            log.info("city 全部匹配");
        }
        log.info("输出城市数量：" + syncCities.size());
        return syncCities;
    }

    protected Set<SyncDistrict> getDisList(List<T> t) {
        List<Jiqi> jiqis = new ArrayList<>();
        for (T a : t) {
            Jiqi jiqi = new Jiqi();
            BeanUtils.copyProperties(a, jiqi);
            jiqis.add(jiqi);
        }


        districts = getCommonData.getDistrict();
        log.info("标准区县数量：" + districts.size());
        log.info("输入区县数量：" + jiqis.size());
        Set<SyncDistrict> syncDistricts = new HashSet<>();
        Set<Jiqi> jiqis1 = new HashSet<>();
        Set<SyncCity> syncCities = new HashSet<>();

        for (District district : districts) {
            threads(jiqis, syncDistricts, jiqis1, district, syncCities);
        }
        int i = jiqis.size() - jiqis1.size();
        if (i > 0) {
            log.info("dis 没有匹配的数量： " + i);
            log.info("dis 没有匹配的数据： ");
            jiqis.removeAll(jiqis1);
            jiqis.forEach(System.out::println);
        } else {
            log.info("dis 全部匹配");
        }
        return syncDistricts;
    }

    public class Thread2 implements Runnable {
        List<Jiqi> jiqis;
        Set<SyncDistrict> syncDistricts;
        District district;
        Set<Jiqi> jiqis1;
        Set<SyncCity> syncCities;

        Thread2(List<Jiqi> jiqis, Set<SyncDistrict> syncDistricts, Set<Jiqi> jiqis1, District district, Set<SyncCity> syncCities) {
            this.jiqis = jiqis;
            this.syncDistricts = syncDistricts;
            this.jiqis1 = jiqis1;
            this.district = district;
            this.syncCities = syncCities;
        }

        @Override
        public void run() {
            threads(jiqis, syncDistricts, jiqis1, district, syncCities);
        }
    }

    private void threads(List<Jiqi> jiqis, Set<SyncDistrict> syncDistricts, Set<Jiqi> jiqis1, District district, Set<SyncCity> syncCities) {
        String districtName = district.getDistrictName();
        String cityName = district.getCityName();
        String provinceName = district.getProvinceName();
        for (Jiqi jiqi : jiqis) {
            if (jiqi.getProname().equals(provinceName)) {
                if (jiqi.getCityname().equals(cityName)) {
                    if (jiqi.getDistrictname().equals(districtName)) {
                        SyncDistrict syncDistrict = new SyncDistrict();
                        syncDistrict.setCityID(district.getCityID());
                        syncDistrict.setDistrictId(district.getId());
                        syncDistrict.setOutDisCode(jiqi.getCitycode());
                        syncDistrict.setDistrictName(districtName);
                        syncDistricts.add(syncDistrict);
                        jiqis1.add(jiqi);
                        continue;
                    }

                    if ("市辖区".equals(jiqi.getDistrictname())) {
                        SyncDistrict syncDistrict = new SyncDistrict();
                        syncDistrict.setOutDisCode(jiqi.getCitycode());

                        //获取第一个区域名称
                        District district1 = getCommonData.getFirstDisByCityCode(district.getCityID());
                        syncDistrict.setDistrictName(district1.getDistrictName());
                        syncDistrict.setDistrictId(district1.getId());
                        syncDistrict.setCityID(district1.getCityID());

                        syncDistricts.add(syncDistrict);
                        jiqis1.add(jiqi);
                    }
                }
            }
        }
    }
}
