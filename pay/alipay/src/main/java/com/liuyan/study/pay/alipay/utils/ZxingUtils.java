package com.liuyan.study.pay.alipay.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyan on 2017/9/30.
 */
@Slf4j
public class ZxingUtils {
    /**
     * 将内容contents生成长为width，宽为width的图片
     */
    public static void getQRCodeImge(String contents, int width, int height, HttpServletResponse httpResponse) {
        try {
            Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            Object put = hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", httpResponse.getOutputStream());


        } catch (Exception e) {
            log.error("create QR code error!", e);

        } finally {
            try {
                httpResponse.getOutputStream().flush();
                httpResponse.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
