package com.mylayui.insurance.controller;

import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.framework.jwt.JWTUtil;
import com.mylayui.insurance.service.UserService;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result main(@RequestBody User user) {

        User entity = userService.login(user.getUserName(), user.getPassword(), user.getType());
        if (entity != null) {
            String token = JWTUtil.sign(entity);
            Map map = new HashMap();
            map.put(JWTUtil.token, token);
            map.put("user", entity);
            map.put("username", entity.getUserName());
            return Result.ok("登录成功", map);
        } else {
            return Result.fail("用户或密码错误");
        }

    }
}
