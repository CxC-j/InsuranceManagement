package com.mylayui.insurance.service;

import com.mylayui.insurance.utils.PaymentVO;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> query(PaymentVO paymentVO);

    int create(PaymentVO paymentVO);

    int update(PaymentVO paymentVO);

    int conCreate(PaymentVO paymentVO);

    int back(PaymentVO paymentVO);

}
