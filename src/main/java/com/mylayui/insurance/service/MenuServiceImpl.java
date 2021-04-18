package com.mylayui.insurance.service;

import com.mylayui.insurance.entity.Menu;
import com.mylayui.insurance.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> query(Integer userId) {
        return menuMapper.query(userId);
    }
}
