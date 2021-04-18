package com.mylayui.insurance.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mylayui.insurance.utils.Pagination;
import lombok.Data;

import java.util.Date;

@Data
public class Notice extends Pagination {
    @TableField(exist = false)
    private User user;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
    private Integer uId;
}
