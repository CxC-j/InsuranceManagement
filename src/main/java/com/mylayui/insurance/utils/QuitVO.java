package com.mylayui.insurance.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QuitVO extends Pagination {
    private Integer id;
    private String name;
    private String idCard;
    private String reason;
    private String receiverName;
    private String receiverCard;
    private String accountNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
}
