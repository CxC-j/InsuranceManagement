package com.mylayui.insurance.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Quit {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cId;
    private String reason;
    private String receiverName;
    private String receiverCard;
    private String accountNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "end_dateC", fill = FieldFill.INSERT)
    private Date startDate;
}
