package com.study.poi.service;

import com.study.poi.commomdata.Province;
import com.study.poi.dao.GetCommonData;
import com.study.poi.dao.GetMine;
import com.study.poi.mine.Jiaoche;
import com.study.poi.mine.Jiqi;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncPro;
import com.study.poi.utils.PoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyan on 2018/3/27.
 */
@Slf4j
@Service
public class InsertTianqi extends BaseService {

    @Autowired
    InsertTData insertTData;

    @Autowired
    GetCommonData getCommonData;

    @Autowired
    GetMine getMine;

    public void insertPro() throws Exception {
        List<Jiaoche> jiqis = null;

        jiqis = PoiUtils.readTQExcel();

        List<SyncPro> provinces1 = getProList(jiqis);
        //插入数据
        insertTData.insertPro(provinces1);
        log.info("excle Pro输入大小 ：" + jiqis.size());
        log.info("excle Pro输出大小 ：" + provinces1.size());

    }

    public void insertCity() throws Exception {
        List<Jiaoche> jiaoches = null;

        jiaoches = PoiUtils.readTQExcel();

        List<SyncCity> syncCities = getCityList(jiaoches);
        //插入数据
        insertTData.insertCity(syncCities);
        log.info("excle city输入大小 ：" + jiaoches.size());
        log.info("excle city插入大小 ：" + syncCities.size());
    }
}
