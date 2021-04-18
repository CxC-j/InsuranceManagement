package com.mylayui.insurance.controller;

import com.mylayui.insurance.entity.Menu;
import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.service.MenuService;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/query")
    public Result query(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Menu> menus = menuService.query(user.getType());
        List<Menu> menuList = new ArrayList<>();
        //找出一级菜单
        for (Menu menu : menus) {
            if (menu.getParentId() == 0) {
                menuList.add(menu);
            }
        }
        //找出子菜单
        for (Menu parent : menuList) {
            List<Menu> child = new ArrayList<>();
            for (Menu entity : menus) {
                if (entity.getParentId() == parent.getId()) {
                    child.add(entity);
                }
            }
            parent.setChild(child);
        }
        return Result.ok(menuList);

    }
}
