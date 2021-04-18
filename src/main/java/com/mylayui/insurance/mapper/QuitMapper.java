package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylayui.insurance.entity.Quit;
import org.apache.ibatis.annotations.Select;

public interface QuitMapper extends BaseMapper<Quit> {
    @Select("SELECT id,c_id,reason,receiver_name,receiver_card,account_number,end_dateC AS startDate FROM quit WHERE c_id = #{cId}")
    Quit queryOne(Integer cId);
}
