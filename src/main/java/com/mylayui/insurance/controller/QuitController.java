package com.mylayui.insurance.controller;

import com.mylayui.insurance.service.QuitService;
import com.mylayui.insurance.utils.QuitVO;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/quit")
public class QuitController {
    @Autowired
    QuitService quitService;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody QuitVO quitVO) {
        Map<String, Object> map = quitService.query(quitVO);
        return Result.okMap(map);
    }

    @PostMapping("create")
    public Result create(@RequestBody QuitVO quitVO) {
        int flag = quitService.create(quitVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody QuitVO quitVO) {
        int flag = quitService.update(quitVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }
}
