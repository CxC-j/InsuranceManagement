package com.mylayui.insurance.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    public void test() {
        menuMapper.query(1);
    }
}