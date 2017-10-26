package com.study.poi.service;

import com.study.poi.dao.GetMine;
import com.study.poi.mine.Jiaoche;
import com.study.poi.sync.SyncCity;
import com.study.poi.sync.SyncPro;
import com.study.poi.utils.PoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyan on 2017/10/17.
 */
@Slf4j
@Service
public class InsertJiaoChe extends BaseService {

    @Autowired
    InsertTData insertTData;

    @Autowired
    GetMine getMine;

    @Value("${getDatafromMysql}")
    Boolean aBoolean;

    public void insertPro() throws Exception {

        List<Jiaoche> jiaoches = null;
        if (aBoolean) {
            jiaoches = getMine.getJiaoche();
        } else {
            jiaoches = PoiUtils.readHQExcel();
        }

        List<SyncPro> provinces1 = getProList(jiaoches);


        //插入数据
        insertTData.insertPro(provinces1);
        log.info("excle pro输入大小 ：" + jiaoches.size());
        log.info("excle pro插入大小 ：" + provinces1.size());
    }


    public void insertCity() throws Exception {
        List<Jiaoche> jiaoches = null;
        if (aBoolean) {
            jiaoches = getMine.getJiaoche();
        } else {
            jiaoches = PoiUtils.readHQExcel();
        }
        List<SyncCity> syncCities = getCityList(jiaoches);
        //插入数据
        insertTData.insertCity(syncCities);
        log.info("excle city输入大小 ：" + jiaoches.size());
        log.info("excle city插入大小 ：" + syncCities.size());
    }
}
