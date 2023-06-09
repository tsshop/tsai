package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.GroupQrCode;
import com.ts.mvc.mapper.GroupQrCodeMapper;
import com.ts.mvc.service.GroupQrCodeService;
import org.springframework.stereotype.Service;

/**
 * 群二维码;(group_qr_code)表服务实现类
 * @author :  tsai
 * @date : 2023-5-12
 */
@Service
public class GroupQrCodeServiceImpl extends ServiceImpl<GroupQrCodeMapper, GroupQrCode> implements GroupQrCodeService {

}