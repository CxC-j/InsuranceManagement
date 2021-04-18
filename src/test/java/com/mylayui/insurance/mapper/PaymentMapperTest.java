package com.mylayui.insurance.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PaymentMapperTest {
    @Autowired
    PaymentMapper paymentMapper;

    @Test
    public void test() {
//        Page<Payment> paymentPage = new Page<>(1,1);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "纪");
        paymentMapper.selectList(queryWrapper);
    }

    @Test
    public void test2() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("c_id", 5);
        List<Payment> paymentList = paymentMapper.selectList(queryWrapper);
        Date date = null;
        Date endDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -4);
        Date needDate = calendar.getTime();
        for (Payment payment : paymentList) {
            if (date == null && endDate == null) {
                date = payment.getStartDate();
                endDate = payment.getEndDate();
            } else {
                date = date.getTime() < payment.getStartDate().getTime() ? date : payment.getStartDate();
                endDate = endDate.getTime() > payment.getEndDate().getTime() ? endDate : payment.getEndDate();
            }
        }
        System.out.println("needDate.getTime():" + needDate.getTime() + "-----" + "endDate.getTime():" + endDate.getTime() + "----new Date().getTime()" + new Date().getTime());
        System.out.println("needDate.getTime():" + needDate.getTime() + "-----" + "endDate.getTime():" + endDate.getTime() + "----new Date().getTime()" + new Date().getTime());
        System.out.println("needDate.getTime():" + needDate.getTime() + "-----" + "endDate.getTime():" + endDate.getTime() + "----new Date().getTime()" + new Date().getTime());

        System.out.println(needDate.getTime() < endDate.getTime() && endDate.getTime() < new Date().getTime());
        System.out.println(date + "----" + endDate);
    }

    @Test
    public void test3() {
        Page<Payment> paymentPage = new Page<>(0, 10);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -4);
        Date needDate = calendar.getTime();
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.ge("end_dateP", needDate);
        queryWrapper1.le("end_dateP", new Date());
        queryWrapper1.eq("status", "正常参保");
        Page<Payment> result = paymentMapper.selectPage(paymentPage, queryWrapper1);
    }
}