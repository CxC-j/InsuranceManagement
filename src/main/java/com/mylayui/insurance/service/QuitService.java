package com.mylayui.insurance.service;

import com.mylayui.insurance.utils.QuitVO;

import java.util.Map;

public interface QuitService {
    Map<String, Object> query(QuitVO quitVO);

    int create(QuitVO quitVO);

    int update(QuitVO quitVO);
}
