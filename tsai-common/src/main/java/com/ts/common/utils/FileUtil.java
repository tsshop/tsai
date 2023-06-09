package com.ts.common.utils;

import cn.hutool.core.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 *  @author : tsai
 * @date : 2023/5/12
 */
public class FileUtil {

    public static String getBase64String(MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            return Base64.encode(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
