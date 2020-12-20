package com.shin.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 图片 base64编码 互转工具类
 */
public class ImageTran {

    /**
     * BufferedImage 编码转换为 base64
     * @param bufferedImage 图片
     * @return String
     */
    public static String BufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
//        System.out.println("值为：" + "data:image/jpg;base64," + png_base64);
        return png_base64;
    }

    /**
     * base64 编码转换为 BufferedImage
     * @param base64 图片的base64编码
     * @return String
     */
    public static BufferedImage base64ToBufferedImage(String base64) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(base64);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            return ImageIO.read(byteArrayInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}