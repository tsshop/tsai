package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}