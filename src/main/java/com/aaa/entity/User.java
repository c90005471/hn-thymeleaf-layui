package com.aaa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;

    private Integer deptId;

    private String loginName;

    private String userName;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String password;

    private String salt;

    private String status;

    private String delFlag;

    private String createBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}