package com.mylayui.insurance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.Notice;
import com.mylayui.insurance.entity.User;
import com.mylayui.insurance.mapper.CanBaoMapper;
import com.mylayui.insurance.service.NoticeService;
import com.mylayui.insurance.service.UserService;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;
    @Autowired
    CanBaoMapper canBaoMapper;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody Notice notice) {
        Page<Notice> noticePage = noticeService.query(notice);
        noticePage.getRecords().forEach(entity -> {
            entity.setUser(userService.queryOne(entity.getUId()));
        });
        return Result.ok(noticePage);
    }

    @PostMapping("create")
    public Result create(@RequestBody Notice notice, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        notice.setUId(user.getId());
        int flag = noticeService.create(notice);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Notice notice) {
        int flag = noticeService.update(notice);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @GetMapping("delete")
    public Result delete(String ids) {
        int flag = noticeService.delete(ids);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @PostMapping("sendAll")
    public Result sendAll(@RequestBody Notice notice) {
        return Result.ok();
//        List<CanBao> canBaoList = canBaoMapper.selectList(null);
//        List<String> phoneNumList = new ArrayList();
//        JSONArray code = new JSONArray();
//        for (CanBao canBao : canBaoList) {
//            String phoneNum = canBao.getPhone1();
//            if (StringUtils.isEmpty(phoneNum)) {
//                phoneNum = canBao.getPhone2();
//            }
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name",canBao.getName());
//            jsonObject.put("msg", notice.getContent());
//            code.add(jsonObject);
//            phoneNumList.add(phoneNum);
//        }
//        //对应的模板代码
//        String templateCode = "templateCode";
//        boolean flag = sendSms.sendAll(phoneNumList, templateCode, code);
//        if (flag) {
//            return Result.ok();
//        } else {
//            return Result.fail("操作失败");
//        }
    }
}
