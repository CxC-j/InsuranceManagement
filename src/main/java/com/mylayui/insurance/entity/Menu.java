package com.mylayui.insurance.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;
    private String title;
    private String icon;
    private String href;
    private String target;
    private Integer parentId;
    private Integer type;
    private List<Menu> child;
}
