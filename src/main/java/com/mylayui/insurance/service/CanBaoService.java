package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.utils.CanBaoVO;

import java.util.Map;

public interface CanBaoService {
    Page<CanBao> query(CanBao canBao);

    int create(CanBao canBao);

    int update(CanBao canBao);

    Map<String, Object> queryAll(CanBaoVO canBaoVO);
}
