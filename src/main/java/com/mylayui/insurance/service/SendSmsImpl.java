package com.mylayui.insurance.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SendSmsImpl implements SendSms {
    //短信发送
    @Override
    public boolean send(String phoneNum, String templateCode, Map<String, Object> code) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI5tHYL5e4Xo2X2kuNRdrD", "tQxBYUe1UJeYfxPHhiKUV5uGRzS9aQ");
        IAcsClient client = new DefaultAcsClient(profile);
        //构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "自己的签名");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendAll(List<String> phoneNumList, String templateCode, JSONArray code) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI5tHYL5e4Xo2X2kuNRdrD", "tQxBYUe1UJeYfxPHhiKUV5uGRzS9aQ");
        IAcsClient client = new DefaultAcsClient(profile);
        //构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        // ["15000000000","15000000001"]
        request.putQueryParameter("PhoneNumbersJson", String.valueOf(JSONArray.parseArray(JSON.toJSONString(phoneNumList))));
        List<String> SignNameList = new ArrayList<>();
        //改成自己的签名
        SignNameList.add("自己的签名");
        String SignNameJson = String.valueOf(JSONArray.parseArray(JSON.toJSONString(SignNameList)));
        request.putQueryParameter("SignNameJson", SignNameJson);
        request.putQueryParameter("TemplateCode", templateCode);
        //[{"code":"1234","product":"ytx1"},{"code":"5678","product":"ytx2"}]
        request.putQueryParameter("TemplateParamJson", code.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
