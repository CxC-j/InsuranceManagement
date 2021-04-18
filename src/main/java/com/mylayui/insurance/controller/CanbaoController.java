package com.mylayui.insurance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylayui.insurance.entity.CanBao;
import com.mylayui.insurance.mapper.CanBaoMapper;
import com.mylayui.insurance.service.CanBaoService;
import com.mylayui.insurance.service.SendSms;
import com.mylayui.insurance.utils.CanBaoVO;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/canbao")
public class CanbaoController {
    @Autowired
    CanBaoService canBaoService;
    @Autowired
    SendSms sendSms;
    @Autowired
    CanBaoMapper canBaoMapper;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody CanBao canBao) {
        Page<CanBao> canBaoPage = canBaoService.query(canBao);
        return Result.ok(canBaoPage);
    }

    @PostMapping("create")
    public Result create(@RequestBody CanBao canBao) {
        int flag = canBaoService.create(canBao);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }

    }

    @PostMapping("update")
    public Result update(@RequestBody CanBao canBao) {
        int flag = canBaoService.update(canBao);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }

    }

    @PostMapping("queryAll")
    public Map<String, Object> queryAll(@RequestBody CanBaoVO canBaoVO) {
        Map<String, Object> map = canBaoService.queryAll(canBaoVO);
        return Result.okMap(map);
    }

    //发送短信
    @PostMapping("send")
    public Result send(@RequestBody CanBaoVO canBaoVO) {
        return Result.ok();
//        String phoneNum = canBaoVO.getPhone1();
//        //对应的模板代码
//        String templateCode = "templateCode";
//        Map<String, Object> code = new HashMap<>();
//        code.put("name", canBaoVO.getName());
//        if (StringUtils.isEmpty(phoneNum)) {
//            phoneNum = canBaoVO.getPhone2();
//        }
//        boolean flag = sendSms.send(phoneNum, templateCode, code);
//        if (flag) {
//            return Result.ok();
//        } else {
//            return Result.fail("操作失败");
//        }
    }

    //群发短信
    @PostMapping("sendAll")
    public Result sendAll() {
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

