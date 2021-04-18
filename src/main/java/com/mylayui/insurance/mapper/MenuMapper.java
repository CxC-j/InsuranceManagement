package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylayui.insurance.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select menu.* from menu,user_menu where menu.id=user_menu.menu_id and" +
            " user_menu.type =#{type}")
    List<Menu> query(Integer type);
}
