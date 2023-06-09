package com.ts.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

public class QRCodeUtil {

    public static String creatRrCode(String contents, int width, int height) {
        String base64 = "";

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            // 1、读取文件转换为字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            //转换成png格式的IO流
            ImageIO.write(image, "png", out);
            byte[] bytes = out.toByteArray();

            // 2、将字节数组转为二进制
            base64 = Base64.encodeBase64String(bytes).trim();

        } catch (WriterException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64;
    }
}