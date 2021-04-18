package com.mylayui.insurance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mylayui.insurance.utils.Pagination;
import lombok.Data;

import java.util.Date;

@Data
public class Payment extends Pagination {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "start_dateP")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "end_dateP")
    private Date endDate;
    private String grade;
    private String status;
}
