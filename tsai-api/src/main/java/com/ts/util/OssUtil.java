package com.ts.util;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.ts.ai.domain.StorageConfig;
import com.ts.ai.service.StorageConfigService;
import com.ts.common.enums.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Description: 阿里云OSS服务器工具类
 * @author: 孙亚威
 * @date: 2020/12/24 16:26
 */
@Component
public class OssUtil {

    public  String uploadFile(StorageConfig storageConfig, MultipartFile file , int time) throws IOException {

        JSONObject config = JSONUtil.parseObj(storageConfig.getConfig());

        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;
        String h=originalFileName.substring(originalFileName.lastIndexOf("."));
        if(h.length()==0){
            h=".jpg";
        }
        String t=new Date().getTime()+(new Random().nextInt(10000)+"");
        String url= t+h;
        byte [] byteArr= file.getBytes();
        InputStream input = new ByteArrayInputStream(byteArr);
        ObjectMetadata meta = new ObjectMetadata();
        if(time!=0)
        {
            Calendar curr = Calendar.getInstance();
            curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+time); //增加几月
            Date date=curr.getTime();
            meta.setExpirationTime(date);//一个月后消失
        }
        uploadFile(config,url,input,meta);
        return config.getStr("domain")+url;
    }

    public  void uploadFile(JSONObject config, String address, InputStream inputStream, ObjectMetadata mate) {
        OSS ossClient = new OSSClientBuilder().build(config.getStr("endpoint"), config.getStr("accessKeyId"), config.getStr("accessKeySecret"));
        ossClient.putObject(config.getStr("bucketName"), address, inputStream,mate);
        ossClient.shutdown();
    }
}
