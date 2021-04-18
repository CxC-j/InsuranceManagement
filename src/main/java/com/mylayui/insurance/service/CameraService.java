package com.mylayui.insurance.service;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CameraService {

    @Value("${download.path:E:/project/养老保险信息管理系统/font/images/download/}")
    private String downloadPath;

    public int takePictures(String idCard) {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            return 0;
        }
        String filePath = downloadPath + "/picture/";
        File path = new File(filePath);
        if (!path.exists()) {//如果文件不存在，则创建该目录
            path.mkdirs();
        }
//        String time = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
//        File file = new File(filePath + "/" + time + ".jpg");
        File file = new File(filePath + "/" + idCard + ".jpg");
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamUtils.capture(webcam, file);
        System.out.println("webcam正打开");
        webcam.close();
        System.out.println("webcam已关闭");

        return 1;
    }

    public int deletePicture(String name) {
        String filePath = downloadPath + "/picture/";
        File path = new File(filePath);
        if (!path.exists()) {//如果文件不存在，则创建该目录
            path.mkdirs();
        }
        File file = new File(filePath + "/" + name + ".jpg");
        if (file.isFile()) {
            file.delete();
            return 1;
        } else {
            return 0;
        }

    }
}
