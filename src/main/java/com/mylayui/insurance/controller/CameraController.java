package com.mylayui.insurance.controller;


import com.mylayui.insurance.service.CameraService;
import com.mylayui.insurance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("tp")
    public Result takePictures(String idCard) {
        int msg = cameraService.takePictures(idCard);
        if (msg == 1) {
            return Result.ok();
        } else {
            return Result.fail("操作失败");
        }
    }
}