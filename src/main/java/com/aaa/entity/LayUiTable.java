package com.aaa.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 16:55
 * @Version 1.0
 */
@Data
public class LayUiTable {
    private int code;
    private String msg;
    private long count;
    private List<?> data;
}
