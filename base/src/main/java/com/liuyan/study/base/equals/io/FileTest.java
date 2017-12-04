package com.liuyan.study.base.equals.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by liuyan on 2017/12/1.
 */
@Slf4j
public class FileTest {

    private static final String TEMP_PATH = System.getProperty("java.io.tmpdir");

    public static void main(String[] args) throws IOException {
        //初始化参数
        File file = new File("C:\\Users\\liuyan\\Desktop\\钛马信息成都分公司户口转移工作流.pdf");
        String fileName = "工作流.pdf";
//        saveFile(file, fileName);
        mySaveFile(file,fileName);
    }

    private static void saveFile(File file, String fileName) throws IOException {
        log.info("fileName : {} ---------- file.getName : {}", fileName, file.getName());
        String suffix = StringUtils.substringAfterLast(fileName, ".");
        fileName = StringUtils.remove(UUID.randomUUID().toString(), "-") + "." + suffix;
        log.info("saveFile----------fileNmae is {}", fileName);
        File destFile = new File(TEMP_PATH + File.separator + fileName);
        FileUtils.copyFile(file, destFile);
    }

    private static void mySaveFile(File file, String fileName) throws IOException {
        String suffix = StringUtils.substringAfterLast(fileName, ".");
        fileName = StringUtils.remove(UUID.randomUUID().toString(), "-") + "." + suffix;
        log.info("saveFile----------mySaveFile is {}", fileName);
        file.renameTo(new File(TEMP_PATH + File.separator + fileName));
//        FileUtils.copyFile(file, destFile);
    }
}
