package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.OrderNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 使用次数订单表;(order_number)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface OrderNumberMapper  extends BaseMapper<OrderNumber>{

    @Update("update order_number set residue_number = residue_number - 1 where id = #{id}")
    Integer deductionNumber(@Param("id") Long id);
}