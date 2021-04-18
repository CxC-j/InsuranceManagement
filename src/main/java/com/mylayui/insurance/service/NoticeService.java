package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.Notice;


public interface NoticeService {
    Page<Notice> query(Notice notice);

    int create(Notice notice);

    int update(Notice notice);

    int delete(String ids);

}
