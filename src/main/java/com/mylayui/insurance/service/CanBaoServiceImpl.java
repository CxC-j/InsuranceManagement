package com.mylayui.insurance.service;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.entity.Payment;
import com.mylayui.insurance.entity.Quit;
import com.mylayui.insurance.mapper.CanBaoMapper;
import com.mylayui.insurance.mapper.PaymentMapper;
import com.mylayui.insurance.mapper.QuitMapper;
import com.mylayui.insurance.utils.CanBaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CanBaoServiceImpl implements CanBaoService {
    @Autowired
    CanBaoMapper canBaoMapper;
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    QuitMapper quitMapper;
    @Autowired
    CameraService cameraService;

    @Override
    public Page<CanBao> query(CanBao canBao) {
        Page<CanBao> canBaoPage = new Page<>(canBao.getPage(), canBao.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!(canBao.getName() == null || canBao.getName().isEmpty())) {
            queryWrapper.eq("name", canBao.getName());
        }
        if (!(canBao.getIdCard() == null || canBao.getIdCard().isEmpty())) {
            queryWrapper.eq("id_card", canBao.getIdCard());
        }
        Page<CanBao> result = canBaoMapper.selectPage(canBaoPage, queryWrapper);
        return result;
    }

    @Override
    public int create(CanBao canBao) {
        canBao.setImage(String.valueOf(canBao.getIdCard()));
        int flag = 0;
        flag = canBaoMapper.insert(canBao);
        return flag;
    }

    @Override
    public int update(CanBao canBao) {
        String newName = String.valueOf(canBao.getIdCard());
        String oldName = canBaoMapper.selectById(canBao.getId()).getImage();
        if (!newName.equals(oldName)) {
            cameraService.deletePicture(oldName);
            canBao.setImage(newName);
        }
        int flag = 0;
        flag = canBaoMapper.updateById(canBao);
        return flag;
    }

    @Override
    public Map<String, Object> queryAll(CanBaoVO canBaoVO) {

        Page<CanBao> canBaoPage = new Page<>(canBaoVO.getPage(), canBaoVO.getLimit());
        //canbao queryWrapper
        QueryWrapper queryWrapper = new QueryWrapper();
        //payment queryWrapper
        Date needDate;
        int total = 0;
        if (!(canBaoVO.getName() == null || canBaoVO.getName().isEmpty())) {
            queryWrapper.eq("name", canBaoVO.getName());
        }
        if (!(canBaoVO.getIdCard() == null || canBaoVO.getIdCard().isEmpty())) {
            queryWrapper.eq("id_card", canBaoVO.getIdCard());
        }
        queryWrapper.orderByAsc("id_card");
        if (!StringUtils.isEmpty(canBaoVO.getStatus())) {
            String status = canBaoVO.getStatus();
            if (status.equals("濒临截止")) {
                Page<Payment> paymentPage = new Page<>(canBaoVO.getPage(), canBaoVO.getLimit());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -4);
                needDate = calendar.getTime();
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.ge("end_dateP", needDate);
                queryWrapper1.le("end_dateP", new Date());
                queryWrapper1.eq("status", "正常参保");
                Page<Payment> result = paymentMapper.selectPage(paymentPage, queryWrapper1);
                List<Payment> paymentList = result.getRecords();
                List<CanBaoVO> canBaoVOList = new ArrayList<>();
                Date date = null;
                Date endDate = null;
                for (Payment payment : paymentList) {
                    Integer cId = payment.getCId();
                    List<Payment> pList = paymentMapper.queryList(cId);
                    for (Payment pl : pList) {
                        if (date == null && endDate == null) {
                            date = pl.getStartDate();
                        } else {
                            date = date.getTime() < pl.getStartDate().getTime() ? date : pl.getStartDate();
                        }
                    }
                    CanBaoVO canBaoVO1 = setCanbaovo(cId, canBaoMapper.selectById(cId), date, payment.getEndDate());
                    canBaoVOList.add(canBaoVO1);
                    date = null;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("count", result.getTotal());
                map.put("data", canBaoVOList);
                return map;
            }
            if (status.equals("正常参保")) {
                Page<Payment> paymentPage = new Page<>(canBaoVO.getPage(), canBaoVO.getLimit());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -4);
                needDate = calendar.getTime();
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("status", "正常参保");
                queryWrapper1.groupBy("c_id");
                Page<Payment> result = paymentMapper.selectPage(paymentPage, queryWrapper1);
                List<Payment> paymentList = result.getRecords();
                List<CanBaoVO> canBaoVOList = new ArrayList<>();
                Date date = null;
                Date endDate = null;
                for (Payment payment : paymentList) {
                    Integer cId = payment.getCId();
                    List<Payment> pList = paymentMapper.queryList(cId);
                    for (Payment pl : pList) {
                        if (date == null && endDate == null) {
                            date = pl.getStartDate();
                            endDate = payment.getEndDate();
                        } else {
                            date = date.getTime() < pl.getStartDate().getTime() ? date : pl.getStartDate();
                            endDate = endDate.getTime() > payment.getEndDate().getTime() ? endDate : payment.getEndDate();

                        }
                    }
                    CanBaoVO canBaoVO1 = setCanbaovo(cId, canBaoMapper.selectById(cId), date, endDate);
                    canBaoVOList.add(canBaoVO1);
                    date = null;
                    endDate = null;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("count", result.getTotal());
                map.put("data", canBaoVOList);
                return map;
            }
            if (status.equals("终止参保")) {
                Page<Payment> paymentPage = new Page<>(canBaoVO.getPage(), canBaoVO.getLimit());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -4);
                needDate = calendar.getTime();
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("status", "终止参保");
                queryWrapper1.groupBy("c_id");
                Page<Payment> result = paymentMapper.selectPage(paymentPage, queryWrapper1);
                List<Payment> paymentList = result.getRecords();
                List<CanBaoVO> canBaoVOList = new ArrayList<>();
                Date date = null;
                Date endDate = null;
                for (Payment payment : paymentList) {
                    Integer cId = payment.getCId();
                    List<Payment> pList = paymentMapper.queryList(cId);
                    for (Payment pl : pList) {
                        if (date == null && endDate == null) {
                            date = pl.getStartDate();
                            endDate = payment.getEndDate();
                        } else {
                            date = date.getTime() < pl.getStartDate().getTime() ? date : pl.getStartDate();
                            endDate = endDate.getTime() > payment.getEndDate().getTime() ? endDate : payment.getEndDate();

                        }
                    }
                    CanBaoVO canBaoVO1 = setCanbaovo(cId, canBaoMapper.selectById(cId), date, endDate);
                    canBaoVOList.add(canBaoVO1);
                    date = null;
                    endDate = null;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("count", result.getTotal());
                map.put("data", canBaoVOList);
                return map;
            }
        }
        Page<CanBao> result = canBaoMapper.selectPage(canBaoPage, queryWrapper);
        List<CanBao> canBaoList = result.getRecords();
        List<CanBaoVO> canBaoVOList = new ArrayList<>();
        Date date = null;
        Date endDate = null;
        for (CanBao canBao : canBaoList) {
            Integer cId = canBao.getId();
            List<Payment> paymentList = paymentMapper.queryList(cId);
            if (paymentList.size() == 1) {
                Date eDate = paymentMapper.queryOne(cId).getEndDate();
                CanBaoVO canBaoVO1 = setCanbaovo(cId, canBao, paymentMapper.queryOne(cId).getStartDate(), eDate);
                canBaoVOList.add(canBaoVO1);
            } else {
                for (Payment payment : paymentList) {
                    if (date == null && endDate == null) {
                        date = payment.getStartDate();
                        endDate = payment.getEndDate();
                    } else {
                        date = date.getTime() < payment.getStartDate().getTime() ? date : payment.getStartDate();
                        endDate = endDate.getTime() > payment.getEndDate().getTime() ? endDate : payment.getEndDate();
                    }
                }
                CanBaoVO canBaoVO1 = setCanbaovo(cId, canBao, date, endDate);
                canBaoVOList.add(canBaoVO1);
                date = null;
                endDate = null;
            }

        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", result.getTotal());
        map.put("data", canBaoVOList);
        return map;
    }

    private CanBaoVO setCanbaovo(Integer cId, CanBao canBao, Date date, Date endDate) {
        CanBaoVO canBaoVO1 = new CanBaoVO();
        Quit quit = quitMapper.queryOne(cId);
        canBaoVO1.setId(canBao.getId());
        canBaoVO1.setName(canBao.getName());
        canBaoVO1.setSex(canBao.getSex());
        canBaoVO1.setBirthday(canBao.getBirthday());
        canBaoVO1.setHouseholdType(canBao.getHouseholdType());
        canBaoVO1.setIdCard(canBao.getIdCard());
        canBaoVO1.setNation(canBao.getNation());
        canBaoVO1.setTown(canBao.getTown());
        canBaoVO1.setVillage(canBao.getVillage());
        canBaoVO1.setAddress(canBao.getAddress());
        canBaoVO1.setCreateDateC(canBao.getCreateDate());
        canBaoVO1.setCategory(canBao.getCategory());
        canBaoVO1.setCreateDateP(date);
        canBaoVO1.setEndDateP(endDate);
        canBaoVO1.setPhone1(canBao.getPhone1());
        canBaoVO1.setPhone2(canBao.getPhone2());
        canBaoVO1.setGrade(canBao.getGrade());
        canBaoVO1.setBack(canBao.getBack());
        if (quit != null) {
            canBaoVO1.setStatus("终止参保");
            canBaoVO1.setEndDateC(quit.getStartDate());
        } else {
            canBaoVO1.setStatus("正常参保");
        }
        return canBaoVO1;
    }
}
