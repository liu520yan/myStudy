package com.study.poi.service;

import com.study.poi.dao.GetCommonData;
import com.study.poi.dao.GetMine;
import com.study.poi.mine.Jiqi;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncDistrict;
import com.study.poi.sync.SyncPro;
import com.study.poi.utils.PoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by liuyan on 2017/10/18.
 */
@Slf4j
@Service
public class InsertJiqi extends BaseService {
    @Autowired
    InsertTData insertTData;

    @Autowired
    GetCommonData getCommonData;

    @Autowired
    GetMine getMine;

//    @Value("getDatafromMysql")
    Boolean aBoolean = false;

    public void insertPro() throws Exception {
        List<Jiqi> jiqis = null;
        if (aBoolean) {
            jiqis = getMine.getJiqi();
        } else {
            jiqis = PoiUtils.readJQExcel();
        }
        List<SyncPro> provinces1 = getProList(jiqis);
        //插入数据
        insertTData.insertPro(provinces1);
        log.info("excle Pro输入大小 ：" + jiqis.size());
        log.info("excle Pro输出大小 ：" + provinces1.size());

    }

    public void insertCity() throws Exception {
        List<Jiqi> jiqis = null;
        if (aBoolean) {
            jiqis = getMine.getJiqi();
        } else {
            jiqis = PoiUtils.readJQExcel();
        }
        List<SyncCity> syncCities = getCityList(jiqis);
        //插入数据
        insertTData.insertCity(syncCities);
        log.info("excle city输入大小 ：" + jiqis.size());
        log.info("excle city输出大小 ：" + syncCities.size());

    }

    public void insertDis() throws Exception {
        List<Jiqi> jiqis = null;
        if (aBoolean) {
            jiqis = getMine.getJiqi();
        } else {
            jiqis = PoiUtils.readJQExcel();
        }
        Set<SyncDistrict> syncDistricts = getDisList(jiqis);
        //插入数据
        insertTData.insertDis(syncDistricts);
        log.info("excle dis输入大小 ：" + jiqis.size());
        log.info("excle dis输出大小 ：" + syncDistricts.size());
    }
}
