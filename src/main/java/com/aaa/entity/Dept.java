package com.aaa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Dept {
    private Integer deptId;

    private String deptName;

    private Integer orderNum;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

}