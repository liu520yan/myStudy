package com.study.poi.utils;

import com.study.poi.mine.Jiaoche;
import com.study.poi.mine.Jiqi;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyan on 2017/10/18.
 */
public class PoiUtils {

    public static List<Jiqi> readJQExcel() throws Exception {
//        String fileName = "C:\\Users\\liuyan\\Desktop\\excel\\区县信息代码表_吉林汽车TDSV2系统.xls";
        String fileName = "C:\\Users\\liuyan\\Desktop\\TDS系统省市码表\\区县信息代码表_吉林汽车TDSV2系统.xls";
        FileInputStream fileInputStream = new FileInputStream(fileName);

        // 1. 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

        // 2. 创建工作类
        HSSFSheet sheet = workbook.getSheetAt(0);

        int sheetCount = sheet.getLastRowNum() - 2;

        List<Jiqi> jiqis = new ArrayList<>();

        for (int i = 0; i < sheetCount; i++) {
            // 3. 创建行 , 第4行 注意:从0开始
            HSSFRow row = sheet.getRow(i + 3);
            // 4. 创建单元格, 第4行第1列 注意:从0开始
            HSSFCell proCode = row.getCell(4);
            HSSFCell proName = row.getCell(5);
            HSSFCell cityCode = row.getCell(2);
            HSSFCell cityName = row.getCell(3);
            HSSFCell disCode = row.getCell(0);
            HSSFCell disName = row.getCell(1);
            HSSFCell useing = row.getCell(10);

            Jiqi jiqi = new Jiqi();
            if ("否".equals(useing.getStringCellValue())) {
                jiqi.setProcode(proCode.getStringCellValue());
                jiqi.setProname(proName.getStringCellValue());
                jiqi.setCitycode(cityCode.getStringCellValue());
                jiqi.setCityname(cityName.getStringCellValue());
                jiqi.setDistrictname(disName.getStringCellValue());
                jiqi.setDistrictode(disCode.getStringCellValue());
                jiqis.add(jiqi);
            }

        }
        return jiqis;
    }

    /**
     * 读取excel
     *
     * @return
     * @throws Exception
     */
    public static List<Jiaoche> readHQExcel() throws Exception {
//        String fileName = "C:\\Users\\liuyan\\Desktop\\excel\\省区和城市基础数据-轿车-红旗.xls";
        String fileName = "C:\\Users\\liuyan\\Desktop\\TDS系统省市码表\\省区和城市基础数据-轿车-红旗.xls";
        FileInputStream fileInputStream = new FileInputStream(fileName);

        // 1. 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

        // 2. 创建工作类
        HSSFSheet sheet = workbook.getSheetAt(0);

        int sheetCount = sheet.getLastRowNum();

        List<Jiaoche> jiqis = new ArrayList<>();
        for (int i = 0; i < sheetCount; i++) {
            // 3. 创建行 , 第4行 注意:从0开始
            HSSFRow row = sheet.getRow(i + 1);
            // 4. 创建单元格, 第4行第1列 注意:从0开始
            HSSFCell proCode = row.getCell(1);
            HSSFCell proName = row.getCell(2);
            HSSFCell cityCode = row.getCell(3);
            HSSFCell cityName = row.getCell(4);

            Jiaoche jiqi = new Jiaoche();
            jiqi.setProcode(proCode.getStringCellValue());
            jiqi.setProname(proName.getStringCellValue());
            jiqi.setCitycode(cityCode.getStringCellValue());
            jiqi.setCityname(cityName.getStringCellValue());
            jiqis.add(jiqi);
        }

        workbook.close();
        fileInputStream.close();
        return jiqis;
    }

    public static void main(String[] args) {
        try {
            readHQExcel();
            readJQExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
