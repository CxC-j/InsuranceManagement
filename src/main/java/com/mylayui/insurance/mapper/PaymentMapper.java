package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylayui.insurance.entity.Payment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaymentMapper extends BaseMapper<Payment> {
    @Select("SELECT id,c_id,start_dateP AS startDate,end_dateP AS endDate,grade FROM payment WHERE c_id=#{cId}")
    List<Payment> queryList(Integer cId);

    @Select("SELECT id,c_id,start_dateP AS startDate,end_dateP AS endDate,grade FROM payment WHERE c_id=#{cId}")
    Payment queryOne(Integer cId);
}
