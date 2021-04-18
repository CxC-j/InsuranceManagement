package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuitMapperTest {
    @Autowired
    QuitMapper quitMapper;

    @Test
    public void test() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("c_id", 1);
        System.out.println(quitMapper.selectOne(queryWrapper).toString());

    }
}