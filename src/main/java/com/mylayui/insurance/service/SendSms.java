package com.mylayui.insurance.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public interface SendSms {
    boolean send(String phoneNum, String templateCode, Map<String, Object> code);

    boolean sendAll(List<String> phoneNum, String templateCode, JSONArray code);
}
