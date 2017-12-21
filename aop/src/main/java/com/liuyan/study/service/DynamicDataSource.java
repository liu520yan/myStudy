package com.liuyan.study.service;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by liuyan on 2017/12/21.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getType();
    }

}
