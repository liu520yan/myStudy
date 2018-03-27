package com.study.poi.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by liuyan on 2018/3/14.
 */
public class PoiUtils1 {


    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\liuyan\\Desktop\\TDS系统省市码表\\区县信息代码表_吉林汽车TDSV2系统.xls";
        FileInputStream fileInputStream = new FileInputStream(fileName);

        // 1. 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

        // 2. 创建工作类
        HSSFSheet sheet = workbook.getSheetAt(0);

        int sheetCount = sheet.getLastRowNum() - 2;



    }

}
