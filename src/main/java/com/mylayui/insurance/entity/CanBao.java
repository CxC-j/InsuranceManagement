package com.mylayui.insurance.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mylayui.insurance.utils.Pagination;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "canbao")
public class CanBao extends Pagination {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String householdType;
    private String idCard;
    private String nation;
    private String town;
    private String village;
    private String address;
    private String phone1;
    private String phone2;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "create_dateC", fill = FieldFill.INSERT)
    private Date createDate;
    private String category;
    private String image;
    private String grade;
    private Integer back;


}
