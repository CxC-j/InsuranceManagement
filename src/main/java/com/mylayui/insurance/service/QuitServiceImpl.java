package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.Payment;
import com.mylayui.insurance.entity.Quit;
import com.mylayui.insurance.mapper.CanBaoMapper;
import com.mylayui.insurance.mapper.PaymentMapper;
import com.mylayui.insurance.mapper.QuitMapper;
import com.mylayui.insurance.utils.QuitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuitServiceImpl implements QuitService {
    @Autowired
    QuitMapper quitMapper;
    @Autowired
    CanBaoMapper canBaoMapper;
    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public Map<String, Object> query(QuitVO quitVO) {
        Page<Quit> quitPage = new Page<>(quitVO.getPage(), quitVO.getLimit());
        //quit表
        QueryWrapper queryWrapper = new QueryWrapper();
        //canbao表
        QueryWrapper queryWrapper1 = new QueryWrapper();

        System.out.println(quitVO.getName());
        if (!(quitVO.getIdCard() == null || quitVO.getIdCard().isEmpty())) {
            queryWrapper1.eq("id_card", quitVO.getIdCard());
            queryWrapper.eq("c_id", canBaoMapper.selectOne(queryWrapper1).getId());
        } else {
            if (!(quitVO.getName() == null || quitVO.getName().isEmpty())) {

                queryWrapper1.eq("name", quitVO.getName());
                queryWrapper.eq("c_id", canBaoMapper.selectOne(queryWrapper1).getId());
            }
        }

        Page<Quit> result = quitMapper.selectPage(quitPage, queryWrapper);
        List<Quit> quitList = result.getRecords();
        List<QuitVO> quitVOList = new ArrayList<>();
        for (Quit quit : quitList) {
            QuitVO quitVO1 = new QuitVO();
            quitVO1.setId(quit.getId());
            quitVO1.setAccountNumber(quit.getAccountNumber());
            quitVO1.setIdCard(canBaoMapper.selectById(quit.getCId()).getIdCard());
            quitVO1.setName(canBaoMapper.selectById(quit.getCId()).getName());
            quitVO1.setEndDate(quit.getStartDate());
            quitVO1.setReason(quit.getReason());
            quitVO1.setReceiverName(quit.getReceiverName());
            quitVO1.setReceiverCard(quit.getReceiverCard());
            quitVOList.add(quitVO1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", result.getTotal());
        map.put("data", quitVOList);
        return map;

    }

    @Override
    public int create(QuitVO quitVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", quitVO.getIdCard());
        Integer c_id = canBaoMapper.selectOne(queryWrapper).getId();
        if (c_id != null) {
            List<Payment> paymentList = paymentMapper.queryList(c_id);
            for (Payment payment : paymentList) {
                payment.setStatus("终止参保");
            }
            Quit quit = new Quit();
            quit.setCId(c_id);
            quit.setStartDate(quitVO.getEndDate());
            quit.setAccountNumber(quitVO.getAccountNumber());
            quit.setReason(quitVO.getReason());
            quit.setReceiverCard(quitVO.getReceiverCard());
            quit.setReceiverName(quitVO.getReceiverName());
            int flag = quitMapper.insert(quit);

            return flag;
        } else {
            return 0;
        }

    }

    @Override
    public int update(QuitVO quitVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_card", quitVO.getIdCard());
        Integer c_id = canBaoMapper.selectOne(queryWrapper).getId();
        if (c_id != null) {
            Quit quit = new Quit();
            quit.setId(quitVO.getId());
            quit.setCId(c_id);
            quit.setStartDate(quitVO.getEndDate());
            quit.setReceiverName(quitVO.getReceiverName());
            quit.setReceiverCard(quitVO.getReceiverCard());
            quit.setReason(quitVO.getReason());
            quit.setAccountNumber(quitVO.getAccountNumber());
            int flag = quitMapper.updateById(quit);
            return flag;
        } else {
            return 0;
        }
    }
}
