package com.mylayui.insurance.service;

import com.mylayui.insurance.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> query(Integer userId);
}
