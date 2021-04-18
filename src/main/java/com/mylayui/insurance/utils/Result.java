package com.mylayui.insurance.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//view object层
@Data
public class Result {
    public static final Integer success_code = 200;
    public static final Integer error_code = 500;
    private Integer code;
    private String msg;
    private Object data = null;


    public Result(Object entity) {
        this.code = success_code;
        this.msg = "操作成功";
        this.data = entity;
    }

    public Result() {
        this.code = success_code;
        this.msg = "操作成功";

    }

    public Result(String msg, Object entity) {
        this.code = success_code;
        this.msg = msg;
        this.data = entity;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //  分页后返回数据
    public static Map<String, Object> ok(Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", success_code);
        map.put("msg", "查询成功");
        map.put("count", page.getTotal());
        map.put("data", page.getRecords());
        return map;
    }

    public static Result fail(String msg) {

        return new Result(error_code, msg);
    }

    public static Result ok(String msg, Object entity) {
        return new Result(msg, entity);
    }

    public static Result ok(Object entity) {
        return new Result(entity);
    }

    public static Result ok() {
        return new Result();
    }

    public static Map<String, Object> okMap(Map map) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", success_code);
        map2.put("msg", "查询成功");
        map2.put("count", map.get("count"));
        map2.put("data", map.get("data"));
        return map2;
    }
}
