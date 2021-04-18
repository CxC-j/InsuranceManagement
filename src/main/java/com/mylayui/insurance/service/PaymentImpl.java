package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.entity.Payment;
import com.mylayui.insurance.mapper.CanBaoMapper;
import com.mylayui.insurance.mapper.PaymentMapper;
import com.mylayui.insurance.utils.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentImpl implements PaymentService {
    @Autowired
    CanBaoMapper canBaoMapper;
    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public Map<String, Object> query(PaymentVO paymentVO) {
        Page<Payment> paymentPage = new Page<>(paymentVO.getPage(), paymentVO.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        QueryWrapper queryWrapper1 = new QueryWrapper();

        System.out.println(paymentVO.getName());
        if (!(paymentVO.getIdCard() == null || paymentVO.getIdCard().isEmpty())) {
            queryWrapper1.eq("id_card", paymentVO.getIdCard());
            queryWrapper.eq("c_id", canBaoMapper.selectOne(queryWrapper1).getId());
        }
        if (!(paymentVO.getGrade() == null || paymentVO.getGrade().isEmpty())) {

            queryWrapper.eq("grade", paymentVO.getGrade());
        }
        queryWrapper.orderByAsc("c_id");
        queryWrapper.orderByAsc("end_dateP");
        Page<Payment> result = paymentMapper.selectPage(paymentPage, queryWrapper);
        List<Payment> paymentList = result.getRecords();
        List<PaymentVO> paymentVOList = new ArrayList<>();
        for (Payment payment : paymentList) {
            CanBao canBao = canBaoMapper.selectById(payment.getCId());
            PaymentVO paymentVO1 = new PaymentVO();
            paymentVO1.setId(payment.getId());
            paymentVO1.setName(canBao.getName());
            paymentVO1.setIdCard(canBao.getIdCard());
            paymentVO1.setEndDate(payment.getEndDate());
            paymentVO1.setStartDate(payment.getStartDate());
            paymentVO1.setGrade(payment.getGrade());
            paymentVO1.setBackGrade(canBao.getGrade());
            paymentVO1.setBack(canBao.getBack());
            paymentVO1.setStatus(payment.getStatus());
            paymentVOList.add(paymentVO1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", result.getTotal());
        map.put("data", paymentVOList);
        return map;
    }

    @Override
    public int create(PaymentVO paymentVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", paymentVO.getIdCard());
        Integer c_id = canBaoMapper.selectOne(queryWrapper).getId();
        if (c_id != null) {
            Payment payment = new Payment();
            payment.setCId(c_id);
            payment.setEndDate(paymentVO.getEndDate());
            payment.setGrade(paymentVO.getGrade());
            payment.setStartDate(paymentVO.getStartDate());
            payment.setStatus("正常参保");
            int flag = paymentMapper.insert(payment);
            return flag;
        } else {
            return 0;
        }

    }

    @Override
    public int update(PaymentVO paymentVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", paymentVO.getIdCard());
        Integer c_id = canBaoMapper.selectOne(queryWrapper).getId();
        if (c_id != null) {
            Payment payment = new Payment();
            payment.setId(paymentVO.getId());
            payment.setCId(c_id);
            payment.setStartDate(paymentVO.getStartDate());
            payment.setEndDate(paymentVO.getEndDate());
            payment.setGrade(paymentVO.getGrade());
            int flag = paymentMapper.updateById(payment);
            return flag;
        } else {
            return 0;
        }
    }

    @Override
    public int conCreate(PaymentVO paymentVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", paymentVO.getIdCard());
        Integer c_id = canBaoMapper.selectOne(queryWrapper).getId();
        if (c_id != null) {
            Payment payment = new Payment();
            payment.setCId(c_id);
            payment.setEndDate(paymentVO.getEndDate());
            payment.setStartDate(paymentVO.getStartDate());
            payment.setGrade(paymentVO.getGrade());
            payment.setStatus("正常参保");
            int flag = paymentMapper.insert(payment);
            return flag;
        } else {
            return 0;
        }

    }

    @Override
    public int back(PaymentVO paymentVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", paymentVO.getIdCard());
        CanBao canBao = canBaoMapper.selectOne(queryWrapper);
        if (canBao != null) {
            canBao.setBack(paymentVO.getBack());
            canBao.setGrade(paymentVO.getBackGrade());
            int flag = canBaoMapper.updateById(canBao);
            return flag;
        } else {
            return 0;
        }


    }
}
