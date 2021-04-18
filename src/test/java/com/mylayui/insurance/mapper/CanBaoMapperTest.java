package com.mylayui.insurance.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.utils.CanBaoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CanBaoMapperTest {
    @Autowired
    CanBaoMapper canBaoMapper;
    @Autowired
    PaymentMapper paymentMapper;

    @Test
    public void test() {
        CanBao canbao = new CanBao();
        canbao.setName("周文芝");

        System.out.println("*********:" + canBaoMapper.insert(canbao));
    }

    @Test
    public void test2() {
        Page<CanBaoVO> iPage = new Page<CanBaoVO>(1, 2);
        Page<CanBaoVO> canBaoVOPage = canBaoMapper.getPageVo(iPage);
        Long total = canBaoVOPage.getTotal();
        int i = 0;

    }

    @Test
    public void tes3() {
        Page<CanBao> canBaoIPage = new Page<>(1, 2);
        Page<CanBao> result = canBaoMapper.selectPage(canBaoIPage, null);
        int i = 0;
    }

    @Test
    public void test4() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "纪");
        canBaoMapper.selectList(queryWrapper);
    }

    @Test
    public void test5() {
        CanBao canBao = new CanBao();
        canBao.setName("xiaoming");
        canBaoMapper.insert(canBao);
        System.out.println(canBao);
    }

    @Test
    public void test6() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", "米波");
        canBaoMapper.selectOne(queryWrapper);
    }

    @Test
    public void test7() {
        CanBao canBao = canBaoMapper.selectById(5);
        canBao.setNation("汉");
        System.out.println("返回数据:" + canBaoMapper.updateById(canBao));
    }

    @Test
    public void test8() {
        Map<String, Object> code = new HashMap<>();
        code.put("name", "123");
        code.put("key", "456");
        List<String> phoneNumList = new ArrayList();
        phoneNumList.add("first");
        phoneNumList.add("second");
        System.out.println(String.valueOf(JSONArray.parseArray(JSON.toJSONString(phoneNumList))));
        System.out.println(JSONArray.parseArray(JSON.toJSONString(phoneNumList)));
        System.out.println("----");
        System.out.println(phoneNumList);
        System.out.println(code);
        System.out.println("----");
        System.out.println(JSONObject.toJSONString(code));

    }

    @Test
    public void test9() {
        List<JSONObject> jsonArray = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "1");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = JSONObject.toJSONString(jsonArray);
        System.out.println(jsonOutput);
    }

    @Test
    public void test10() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date needDate = calendar.getTime();
//        needDate.getTime() < paymentMapper.queryOne(4).getEndDate().getTime() && paymentMapper.queryOne(4).getEndDate().getTime() < new Date().getTime()
        System.out.println((calendar.getTime()).getTime());
        System.out.println(calendar.getTime());
        System.out.println(calendar.getTime());
    }
}