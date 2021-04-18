package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.User;


public interface UserService {
    User login(String username, String password, Integer type);

    Page<User> query(User user);

    int create(User user);

    int update(User user);

    int delete(String ids);

    User queryOne(Integer id);

}
