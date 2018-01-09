package com.liuyan.utils.zip;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liuyan on 2018/1/4.
 */
public class BufferedImageTest {

    public static boolean resize(String src, String to, int newWidth, int newHeight) {
        try {
            File srcFile = new File(src);
            File toFile = new File(to);
            BufferedImage img = ImageIO.read(srcFile);
            int w = img.getWidth();
            int h = img.getHeight();
            BufferedImage dimg = new BufferedImage(newWidth, newHeight, img.getType());
            Graphics2D g = dimg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, w, h, null);
            g.dispose();
            ImageIO.write(dimg, "jpg", toFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 模糊算法
     *
     * @param src     源文件地址
     * @param target  目标文件地址
     * @param quality 比例
     * @return
     */
    public static boolean compress(String src, String target, float quality) {
        boolean rs = true;

        // Build param
        JPEGEncodeParam param = null;

        // Build encoder
        File destination = new File(target);
        FileOutputStream os = null;
        try {
            System.out.println(new File(src).length());

            BufferedImage image = ImageIO.read(new File(src));
            param = JPEGCodec.getDefaultJPEGEncodeParam(image);
            param.setQuality(quality, false);

            os = FileUtils.openOutputStream(destination);
            JPEGImageEncoder encoder;
            if (param != null) {
                encoder = JPEGCodec.createJPEGEncoder(os, param);
            } else {
                return false;
            }
            encoder.encode(image);
            System.out.println(destination.length());
        } catch (Exception e) {
            e.printStackTrace();
            rs = false;
        } finally {
            IOUtils.closeQuietly(os);
        }
        return rs;
    }

    public static void s(String src, String target) {

        float largestLength = 500 * 1024 * 1.0f;
        float srcLength = new File(src).length() * 1.0f;
        float quality = largestLength / srcLength;

        compress(src, target, quality);
    }

    public static void main(String[] args) throws IOException {
//        test2();
//        test3();
//        test4();https://github.com/coobird/thumbnailator/wiki/Examples
        Thumbnails.of("E:\\pic\\1.jpg")
                .width(960)
                .height(560)
                .outputQuality(0.5f)
                .toFile("E:\\pic\\2.jpg");
    }

    private static void test4() {
        s("E:\\pic\\1.jpg", "E:\\pic\\2.jpg");
    }

    private static void test3() {
        compress("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg", "C:\\Users\\liuyan\\Desktop\\pic\\3.jpg", 0.2f);
    }

    private static void test2() {
        resize("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg", "C:\\Users\\liuyan\\Desktop\\pic\\2.jpg", 900, 500);
    }
}
