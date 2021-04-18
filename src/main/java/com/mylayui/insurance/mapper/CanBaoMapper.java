package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.utils.CanBaoVO;
import org.apache.ibatis.annotations.Select;

public interface CanBaoMapper extends BaseMapper<CanBao> {
    @Select("select canbao.*,quit.end_date from canbao,quit where canbao.id=quit.c_id")
    Page<CanBaoVO> getPageVo(Page<CanBaoVO> iPage);
}
