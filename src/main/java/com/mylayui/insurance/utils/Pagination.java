package com.mylayui.insurance.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Pagination {
    @TableField(exist = false)
    private Integer page;
    @TableField(exist = false)
    private Integer limit;
}
