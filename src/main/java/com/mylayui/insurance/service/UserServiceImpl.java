package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password, Integer type) {
        QueryWrapper QueryWrapper = new QueryWrapper();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", username);
        map.put("password", password);
        map.put("type", type);
        QueryWrapper.allEq(map);
        User user = userMapper.selectOne(QueryWrapper);
        return user;
    }

    @Override
    public Page<User> query(User user) {
        Page<User> userPage = new Page<>(user.getPage(), user.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!(user.getUserName() == null || user.getUserName().isEmpty())) {
            queryWrapper.eq("user_name", user.getUserName());
        }
        if (!(user.getType() == null)) {
            queryWrapper.eq("type", user.getType());
        }
        Page<User> result = userMapper.selectPage(userPage, queryWrapper);
        return result;
    }

    @Override
    public int create(User user) {
        int flag = 0;
        flag = userMapper.insert(user);
        return flag;

    }

    @Override
    public int update(User user) {
        int flag = 0;
        flag = userMapper.updateById(user);
        return flag;

    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            if (!s.isEmpty()) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", Integer.parseInt(s));
                userMapper.delete(queryWrapper);
                flag++;
            }
        }
        return flag;
    }

    @Override
    public User queryOne(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }


}
