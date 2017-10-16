package com.liuyan.utils.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by teeyoung on 17/9/8.
 */
public class ZXingTools {

    public static void main(String[] args) {

        String contents = "http://www.dubby.cn/detail.html?id=9018";

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix = null;

        try {
            matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, 300, 300, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        File file = new File("qrcodeImage.png");
        try {
            MatrixToImageWriter.writeToPath(matrix, "png", file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
