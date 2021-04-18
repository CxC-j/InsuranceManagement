package com.mylayui.insurance.controller;

import com.mylayui.insurance.service.PaymentService;
import com.mylayui.insurance.utils.PaymentVO;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody PaymentVO paymentVO) {
        Map<String, Object> map = paymentService.query(paymentVO);
        return Result.okMap(map);
    }

    @PostMapping("create")
    public Result create(@RequestBody PaymentVO paymentVO) {

        int flag = paymentService.create(paymentVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }

    }

    @PostMapping("update")
    public Result update(@RequestBody PaymentVO paymentVO) {
        int flag = paymentService.update(paymentVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @PostMapping("conCreate")
    public Result conCreate(@RequestBody PaymentVO paymentVO) {
        int flag = paymentService.conCreate(paymentVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

    @PostMapping("back")
    public Result back(@RequestBody PaymentVO paymentVO) {
        int flag = paymentService.back(paymentVO);
        if (flag > 0) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }

}
