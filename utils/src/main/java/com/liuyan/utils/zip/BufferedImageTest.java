package com.liuyan.utils.zip;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

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


    public static boolean zipImage(String srcImagePath, String destImagePath, int zipImageWidth) {
        String imageType = FilenameUtils.getExtension(srcImagePath);

        try {
            Image image = ImageIO.read(new File(srcImagePath));
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            int zipImageHeight = zipImageWidth * height / width;
            BufferedImage bi = new BufferedImage(zipImageWidth, zipImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            boolean result = g.drawImage(image, 0, 0, zipImageWidth, zipImageHeight, null);
            g.dispose();

            return result && ImageIO.write(bi, imageType, new File(destImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 模糊算法
     *
     * @param src     源文件地址
     * @param target  目标文件地址
     * @param quality 质量
     * @return
     */
    private static boolean compress(String src, String target, float quality) {
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
            os = new FileOutputStream(destination);
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

    public static void zipByQuality(String src, String target) {

        float largestLength = 500 * 1024 * 1.0f;
        float srcLength = new File(src).length() * 1.0f;
        float quality = largestLength / srcLength;
        System.out.println(quality);
        compress(src, target, quality);
    }

    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        javaSizeZip();
        long t2 = System.currentTimeMillis();
        System.out.println("javaSizeZip time :" + (t2 - t1));
        javaQualityZip();
        long t3 = System.currentTimeMillis();
        System.out.println("javaQualityZip time :" + (t3 - t2));
        googleZip(new File("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg"), "C:\\Users\\liuyan\\Desktop\\pic\\google.jpg");
        long t4 = System.currentTimeMillis();
        System.out.println("googleZip time :" + (t4 - t3));
    }

    private static void googleZip(File file, String newFilePath) throws IOException {
        Thumbnails.of(file)
                .width(900)
                .height(1200)
                .outputQuality(1f)
                .toFile(newFilePath);
    }


    private static void javaQualityZip() {
        compress("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg", "C:\\Users\\liuyan\\Desktop\\pic\\javaQualityZip.jpg", 0.5f);
    }

    private static void javaSizeZip() {
        File file = new File("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg");
        zipImage("C:\\Users\\liuyan\\Desktop\\pic\\1.jpg", "C:\\Users\\liuyan\\Desktop\\pic\\javaSizeZip.jpg", 900);
    }
}
