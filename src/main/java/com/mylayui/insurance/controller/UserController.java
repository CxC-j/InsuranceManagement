package com.mylayui.insurance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.service.UserService;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody User user) {
        Page<User> userPage = userService.query(user);
        return Result.ok(userPage);
    }

    @PostMapping("create")
    public Result create(@RequestBody User user) {
        int flag = userService.create(user);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }

    }

    @PostMapping("update")
    public Result update(@RequestBody User user) {
        int flag = userService.update(user);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }

    }

    @GetMapping("delete")
    public Result delete(String ids) {
        int flag = userService.delete(ids);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return Result.ok(user);
    }

    @PostMapping("pwd")
    public Result pwd(@RequestBody Map<String, String> map, HttpServletRequest request) {
        String spassword = map.get("spassword");
        String npassword = map.get("npassword");
        //用户修改
        if (request.getAttribute("user") != null) {
            User user = (User) request.getAttribute("user");
            User entity = userService.queryOne(user.getId());
            if (entity.getPassword().equals(spassword)) {
                User param = new User();
                param.setId(entity.getId());
                param.setPassword(npassword);
                userService.update(param);
                return Result.ok("修改密码成功");
            } else {
                return Result.fail("原密码错误");
            }
        }
        return Result.ok();
    }
}