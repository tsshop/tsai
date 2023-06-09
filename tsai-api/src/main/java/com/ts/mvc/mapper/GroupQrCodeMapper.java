package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.GroupQrCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群二维码;(group_qr_code)表数据库访问层
 * @author :  tsai
 * @date : 2023-5-12
 */
@Mapper
public interface GroupQrCodeMapper  extends BaseMapper<GroupQrCode>{
}