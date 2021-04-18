package com.mylayui.insurance.framework.mvc;

import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.framework.exception.MyException;
import com.mylayui.insurance.framework.jwt.JWTUtil;
import com.mylayui.insurance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JWTUtil.token);
        User user = JWTUtil.getUser(token);
        user = userMapper.selectById(user.getId());
        System.out.println(user.getType() + "--" + user.getUserName());
        if (user == null) {
            throw new MyException("超时或不合法的token");
        }

        String newToken = JWTUtil.sign(user);
        response.setHeader(JWTUtil.token, newToken);
        response.setHeader("Access-Control-Expose-Headers", JWTUtil.token);
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
