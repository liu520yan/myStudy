package com.liuyan.study.base.equals;

import java.io.*;

/**
 * Created by liuyan on 2018/3/16.
 */
public class Yulei {

    public static void main(String[] args) throws IOException {
        File result = new File("/tmp/result");
        File ids = new File("/tmp/id.txt");
        File contracts = new File("/tmp/orderInfo.log");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ids)));

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result)));
        String line;
        String contractLine;
        int idLineNumber = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println("id:" + line);
            BufferedReader contractReader = new BufferedReader(new InputStreamReader(new FileInputStream(contracts)));
            while ((contractLine = contractReader.readLine()) != null) {
                if (contractLine.contains(line)) {
                    int indexOf = contractLine.indexOf("contractNo");
                    String contractNo = contractLine.substring(indexOf + 13, indexOf + 13 + 18);
                    System.out.println("id:" + line + "=====>contractNo:" + contractNo);
                    bufferedWriter.write(contractNo);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
            contractReader.close();
            bufferedWriter.flush();
            idLineNumber++;
        }
        System.out.println("id行数:" + idLineNumber);
        bufferedWriter.flush();
        reader.close();

        bufferedWriter.close();
    }
}
