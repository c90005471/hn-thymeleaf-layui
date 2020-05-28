package com.aaa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {
    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String url;

    private String menuType;

    private String visible;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}