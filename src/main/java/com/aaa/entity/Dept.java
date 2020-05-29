package com.aaa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    private Date updateTime;

}