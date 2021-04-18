package com.mylayui.insurance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.Notice;
import com.mylayui.insurance.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Page<Notice> query(Notice notice) {
        Page<Notice> noticePage = new Page<>(notice.getPage(), notice.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!(notice.getTitle() == null || notice.getTitle().isEmpty())) {
            queryWrapper.like("title", notice.getTitle());
            System.out.println("--------");
        }
//
        Page<Notice> result = noticeMapper.selectPage(noticePage, queryWrapper);
        return result;
    }

    @Override
    public int create(Notice notice) {
        int flag = 0;
        flag = noticeMapper.insert(notice);
        return flag;
    }

    @Override
    public int update(Notice notice) {
        int flag = 0;
        flag = noticeMapper.updateById(notice);
        return flag;
    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            if (!s.isEmpty()) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", Integer.parseInt(s));
                noticeMapper.delete(queryWrapper);
                flag++;
            }
        }
        return flag;
    }
}
