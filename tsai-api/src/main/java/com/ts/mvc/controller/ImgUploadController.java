package com.ts.mvc.controller;



import com.ts.ai.domain.StorageConfig;
import com.ts.common.core.domain.AjaxResult;
import com.ts.common.enums.SystemConstant;
import com.ts.config.token.PassToken;
import com.ts.util.LocalStorageUtils;
import com.ts.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : tsai
 * @since 2021/3/4
 * 图片上传请求
 */
@RestController
@RequestMapping("/file")
public class ImgUploadController {
    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private LocalStorageUtils localStorageUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 单个图片上传
     */
    @PostMapping("/upload")
    @PassToken
    public AjaxResult fileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {

        StorageConfig config = (StorageConfig) redisTemplate.opsForValue().get("storage");

        if (SystemConstant.StorageTypeEnum.ALI_OSS.getCode().equals(config.getType())){
            try{
                String imgUrl = ossUtil.uploadFile(config,file,0);
                return new AjaxResult(HttpStatus.OK.value(), "成功", imgUrl);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (SystemConstant.StorageTypeEnum.LOCAL_STORAGE.getCode().equals(config.getType())){
            try{
                String imgUrl = localStorageUtils.uploadFile(file);
                return new AjaxResult(HttpStatus.OK.value(), "成功", imgUrl);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败");
    }

}
